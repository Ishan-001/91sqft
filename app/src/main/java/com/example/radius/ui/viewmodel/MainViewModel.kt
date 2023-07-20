package com.example.radius.ui.viewmodel

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.database.Cursor
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radius.data.network.model.DataItem
import com.example.radius.data.repository.Repository
import com.example.radius.ui.model.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val screenState = mutableStateOf(ScreenState.Loading)

    var progress = 0

    var twoDFiles = mutableStateListOf<DataItem>()
    var threeDFiles = mutableStateListOf<DataItem>()
    var prodFiles = mutableStateListOf<DataItem>()

    val downloadedFiles = mutableStateListOf<DataItem>()
    var currentFile by mutableStateOf<DataItem?>(null)

    init {
        getData()
    }

    private fun getData() {
        repository.getData().onEach { dataState ->
            screenState.value = ScreenState.Loading
            dataState.data?.let { response ->
                twoDFiles.addAll(response.data.filter {
                    it.section == "2D"
                })
                threeDFiles.addAll(response.data.filter {
                    it.section == "3D"
                })
                prodFiles.addAll(response.data.filter {
                    it.section == "PROD"
                })

                screenState.value = ScreenState.Success
            }
            dataState.error?.let {
                screenState.value = ScreenState.Error
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("Range")
    fun downloadFile(item: DataItem, context: Context) {
        currentFile = item

        val url = item.file
        val dirPath: String =
            ("${context.filesDir.absolutePath}${File.separator}").toString() + "example/${System.currentTimeMillis()}"
        val file = File(dirPath)

        val request = DownloadManager.Request(Uri.parse(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
            .setDestinationUri(Uri.fromFile(file))
            .setTitle(file.name)
            .setDescription("Downloading")
            .setRequiresCharging(false)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val downloadID = downloadManager.enqueue(request)

        var finishDownload = false

        while (!finishDownload) {
            val cursor: Cursor =
                downloadManager.query(DownloadManager.Query().setFilterById(downloadID))
            if (cursor.moveToFirst()) {
                when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    DownloadManager.STATUS_FAILED -> {
                        finishDownload = true
                    }
                    DownloadManager.STATUS_PAUSED -> {}
                    DownloadManager.STATUS_PENDING -> {}
                    DownloadManager.STATUS_RUNNING -> {
                        val total: Long =
                            cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                        if (total >= 0) {
                            val downloaded: Long =
                                cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                            progress = (downloaded * 100L / total).toInt()
                        }
                    }
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        progress = 100
                        finishDownload = true

                        downloadedFiles.add(item)
                    }
                }
            }
        }
    }
}
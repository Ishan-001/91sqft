package com.example.radius.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radius.R
import com.example.radius.ui.component.ListItem
import com.example.radius.ui.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    var is2dListExpanded by remember {
        mutableStateOf(false)
    }
    var is3dListExpanded by remember {
        mutableStateOf(false)
    }
    var isProdListExpanded by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    vertical = 15.dp,
                    horizontal = 22.dp
                )
        ) {
            Text(
                text = "Design Layout",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append(text = "CLIENT")
                    }
                    withStyle(
                        SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(text = "   Bridgestone   ")
                    }
                    withStyle(
                        SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append(text = "|  JOB ID  ")
                    }
                    withStyle(
                        SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(text = "BRID1337")
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0xFFF9F9F9))
                .padding(20.dp)
        ) {
            LazyColumn {
                stickyHeader {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "2D Layout/Adaptation  ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier
                                .background(Color(0xFFEBEBEB)),
                            text = "${viewModel.twoDFiles.size} files"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier.clickable {
                                is2dListExpanded = !is2dListExpanded
                            },
                            painter = painterResource(id = R.drawable.ic_dropdown),
                            contentDescription = null,
                        )
                    }
                }

                items(viewModel.twoDFiles) {
                    ListItem(
                        dataItem = it,
                        onClick = { item ->
                            viewModel.downloadFile(item, context)
                        },
                        isDownloaded = viewModel.downloadedFiles.contains(it),
                        isDownloading = viewModel.currentFile == it,
                        progress = viewModel.progress
                    )
                }

                stickyHeader {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "3D Layout/Adaptation  ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier
                                .background(Color(0xFFEBEBEB)),
                            text = "${viewModel.threeDFiles.size} files"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier.clickable {
                                is3dListExpanded = !is3dListExpanded
                            },
                            painter = painterResource(id = R.drawable.ic_dropdown),
                            contentDescription = null,
                        )
                    }
                }

                items(viewModel.threeDFiles) {
                    ListItem(
                        dataItem = it,
                        onClick = { url ->
                            viewModel.downloadFile(url, context)
                        },
                        isDownloaded = viewModel.downloadedFiles.contains(it),
                        isDownloading = viewModel.currentFile == it,
                        progress = viewModel.progress
                    )
                }

                stickyHeader {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Production Files/Artworks  ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier
                                .background(Color(0xFFEBEBEB)),
                            text = "${viewModel.prodFiles.size} files"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier.clickable {
                                isProdListExpanded = !isProdListExpanded
                            },
                            painter = painterResource(id = R.drawable.ic_dropdown),
                            contentDescription = null,
                        )
                    }
                }

                items(viewModel.prodFiles) {
                    ListItem(
                        dataItem = it,
                        onClick = { url ->
                            viewModel.downloadFile(url, context)
                        },
                        isDownloaded = viewModel.downloadedFiles.contains(it),
                        isDownloading = viewModel.currentFile == it,
                        progress = viewModel.progress
                    )
                }
            }
        }
    }
}
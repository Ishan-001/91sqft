package com.example.radius.data.repository

import com.example.radius.data.network.response.DataState
import com.example.radius.data.network.response.parse
import com.example.radius.data.network.service.Service
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Repository(
    private val service: Service,
) {
    internal fun getData() = flow {
        service.getData().parse {
            emit(DataState.success(it))
        }
    }.catch {
        emit(DataState.error(it))
    }
}
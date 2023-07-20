package com.example.radius.data.repository

import com.example.radius.data.network.response.DataState
import com.example.radius.data.network.service.Service
import io.realm.Realm
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Repository(
    private val service: Service,
    private val realm: Realm,
    private val currentTimestamp: Long
) {
    internal fun getFacilities() = flow {

    }.catch {
        emit(DataState.error(it))
    }
}
package com.example.radius.data.network.response

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> Response<T>.parse(
    onSuccess: suspend (T) -> Unit
) {
    Log.d("Ishan", this.message() + this.isSuccessful)
    if (isSuccessful) {
        body()?.let { onSuccess(it) }
    } else {
        throw HttpException(this)
    }
}

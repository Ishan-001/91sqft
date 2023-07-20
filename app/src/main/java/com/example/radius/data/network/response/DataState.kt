package com.example.radius.data.network.response

data class DataState<out T>(
    val data: T? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val currentPage: Int = 1,
    val totalPages: Int = 1
) {
    companion object {
        fun <T> success(data: T): DataState<T> =
            DataState(data = data)

        fun <T> loading(): DataState<T> =
            DataState(isLoading = true)

        fun <T> error(throwable: Throwable): DataState<T> =
            DataState(error = throwable.message)
    }
}
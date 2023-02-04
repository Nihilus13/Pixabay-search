package com.nihilus13.coroutines.safeapi

sealed class SafeApiResponse<out T> {
    open fun getData(): T? = null

    class Success<T>(private val data: T) : SafeApiResponse<T>() {
        override fun getData(): T = data
    }

    class Failure(val exception: Exception) : SafeApiResponse<Nothing>()
}

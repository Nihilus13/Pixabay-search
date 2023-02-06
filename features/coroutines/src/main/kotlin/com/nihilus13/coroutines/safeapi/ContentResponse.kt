package com.nihilus13.coroutines.safeapi

sealed class ContentResponse<out T> {
    open fun getData(): T? = null

    data class Success<T>(private val data: T) : ContentResponse<T>() {
        override fun getData(): T = data
    }

    data class Failure(val exception: Exception) : ContentResponse<Nothing>()
}

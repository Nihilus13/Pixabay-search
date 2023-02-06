package com.nihilus13.coroutines.safeapi

import android.util.Log

@Suppress("TooGenericExceptionCaught")
suspend fun <T> asContentResponse(contentResponseFunction: suspend () -> T): ContentResponse<T> =
    try {
        val response = contentResponseFunction()
        ContentResponse.Success(response)
    } catch (e: Exception) {
        Log.d(e.message, e.stackTraceToString())
        ContentResponse.Failure(e)
    }

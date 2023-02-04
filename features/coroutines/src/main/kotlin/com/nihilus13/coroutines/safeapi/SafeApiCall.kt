package com.nihilus13.coroutines.safeapi

import android.util.Log

@Suppress("TooGenericExceptionCaught")
inline fun <T> safeApiCall(responseFunction: () -> T): SafeApiResponse<T> =
    try {
        val response = responseFunction()
        SafeApiResponse.Success(response)
    } catch (e: Exception) {
        Log.d(e.message, e.stackTraceToString())
        SafeApiResponse.Failure(e)
    }

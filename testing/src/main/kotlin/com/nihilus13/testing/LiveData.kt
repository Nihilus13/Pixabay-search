package com.nihilus13.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> LiveData<T>.observeValues(): List<T> {
    val data = mutableListOf<T>()
    observeForever { data.add(it) }
    return data
}

fun observeValues(vararg liveData: LiveData<out Any>): List<Any> =
    MediatorLiveData<Any>().apply {
        liveData.forEach { addSource(it, ::setValue) }
    }.observeValues()

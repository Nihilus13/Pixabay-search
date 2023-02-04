package com.nihilus13.coroutines.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface TestableCoroutineScope : CoroutineScope {
    fun launch(block: suspend CoroutineScope.() -> Unit): Job
}

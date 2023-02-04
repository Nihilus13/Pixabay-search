package com.nihilus13.coroutines.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LifecycleScope internal constructor(
    private val coroutineScope: CoroutineScope,
    override val coroutineContext: CoroutineContext
) : TestableCoroutineScope {
    constructor(coroutineScope: CoroutineScope) : this(
        coroutineScope,
        coroutineScope.coroutineContext
    )

    override fun launch(block: suspend CoroutineScope.() -> Unit): Job =
        coroutineScope.launch(coroutineContext, block = block)
}

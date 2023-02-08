package com.nihilus13.pixabay.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

internal interface StateObserver<STATE> {

    @Suppress("UNCHECKED_CAST")
    fun observeState(owner: LifecycleOwner, onChanged: (STATE) -> Unit) {
        val observer: Observer<STATE> = Observer { onChanged(it) }
        (testState as? STATE)?.run { observer.onChanged(this) } ?: bindStateObserver(
            owner,
            observer
        )
    }

    fun bindStateObserver(owner: LifecycleOwner, observer: Observer<STATE>)

    companion object {
        var testState: Any? = null
    }
}

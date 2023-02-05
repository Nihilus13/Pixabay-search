package com.nihilus13.scaffold.adapter.diff

data class NullablePayload<T>(val value: T?)

fun <T> NullablePayload<T>?.applyIfNonNull(action: (T?) -> Unit) {
    if (this != null) {
        action(value)
    }
}

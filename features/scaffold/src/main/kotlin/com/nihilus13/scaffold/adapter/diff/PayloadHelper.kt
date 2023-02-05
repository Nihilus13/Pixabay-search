package com.nihilus13.scaffold.adapter.diff

fun <T> alwaysInPayload(newValue: T, oldValue: T): T =
    if (oldValue != newValue) newValue else oldValue

fun <T> allwaysNullableInPayload(newValue: T?, oldValue: T?): NullablePayload<T>? =
    if (oldValue != newValue) NullablePayload(newValue) else NullablePayload(oldValue)

fun <T> payload(newValue: T, oldValue: T): T? =
    if (oldValue != newValue) newValue else null

fun <T> nullablePayload(newValue: T?, oldValue: T?): NullablePayload<T>? =
    if (oldValue != newValue) NullablePayload(newValue) else null
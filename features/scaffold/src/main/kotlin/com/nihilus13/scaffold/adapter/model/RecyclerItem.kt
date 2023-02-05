package com.nihilus13.scaffold.adapter.model

import android.os.Parcelable

interface RecyclerItem : Parcelable {

    val id: String

    val viewType: Int

    fun areItemsTheSame(item: RecyclerItem): Boolean =
        id == item.id && viewType == item.viewType

    fun calculatePayload(item: RecyclerItem): Any?
}
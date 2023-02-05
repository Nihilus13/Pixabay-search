package com.nihilus13.scaffold.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nihilus13.scaffold.adapter.model.RecyclerItem

internal class CommonDiffItemCallback<ITEM : RecyclerItem> : DiffUtil.ItemCallback<ITEM>() {

    override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        oldItem.equals(newItem)

    override fun getChangePayload(oldItem: ITEM, newItem: ITEM): Any? =
        oldItem.calculatePayload(newItem)
}
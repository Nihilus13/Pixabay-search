package com.nihilus13.scaffold.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nihilus13.scaffold.adapter.model.RecyclerItem

abstract class AbstractViewHolder<in RECYCLER_ITEM : RecyclerItem>(binding: View) :
    RecyclerView.ViewHolder(binding) {

    abstract fun bind(item: RECYCLER_ITEM)

    abstract fun onPayload(payloads: List<Any>)
}

package com.nihilus13.scaffold.adapter.delegate

import android.view.ViewGroup
import com.nihilus13.scaffold.adapter.holder.AbstractViewHolder
import com.nihilus13.scaffold.adapter.model.RecyclerItem

abstract class AbstractViewHolderDelegate<VIEW_HOLDER : AbstractViewHolder<RECYCLER_ITEM>, in RECYCLER_ITEM : RecyclerItem, BINDING>
constructor(private val inflateBinding: (ViewGroup) -> BINDING) {

    abstract fun getViewHolderType(): Int

    abstract fun createViewHolder(parent: ViewGroup): VIEW_HOLDER

    abstract fun typeCheck(recyclerItem: RecyclerItem): Boolean

    fun createBinding(parent: ViewGroup): BINDING = inflateBinding(parent)

    fun isInstanceOfBinder(
        item: RecyclerItem,
        func: (RecyclerItem) -> Boolean = { typeCheck(it) }
    ): Boolean = func(item)
}

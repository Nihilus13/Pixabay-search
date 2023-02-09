package com.nihilus13.scaffold.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nihilus13.scaffold.adapter.delegate.DelegateType
import com.nihilus13.scaffold.adapter.holder.AbstractViewHolder
import com.nihilus13.scaffold.adapter.model.RecyclerItem

class CommonAdapter(private val setViewHolderBinders: Set<DelegateType>) :
    RecyclerView.Adapter<AbstractViewHolder<RecyclerItem>>() {

    private val asyncDiff = AsyncListDiffer(this, CommonDiffItemCallback())

    var list: List<RecyclerItem>
        get() = asyncDiff.currentList
        set(value) = asyncDiff.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<RecyclerItem> =
        setViewHolderBinders
            .first { it.getViewHolderType() == viewType }
            .createViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int =
        setViewHolderBinders.firstOrNull { it.isInstanceOfBinder(list[position]) }
            ?.getViewHolderType() ?: -1

    override fun onBindViewHolder(holder: AbstractViewHolder<RecyclerItem>, position: Int) =
        list[position].let { holder.bind(it) }

    override fun onBindViewHolder(
        holder: AbstractViewHolder<RecyclerItem>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            holder.onPayload(payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }
}

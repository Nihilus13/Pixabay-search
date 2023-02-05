package com.nihilus13.scaffold.adapter.delegate

import androidx.databinding.ViewDataBinding
import com.nihilus13.scaffold.adapter.holder.AbstractViewHolder
import com.nihilus13.scaffold.adapter.model.RecyclerItem

typealias DelegateType = AbstractViewHolderDelegate<out AbstractViewHolder<RecyclerItem>, RecyclerItem, out ViewDataBinding>
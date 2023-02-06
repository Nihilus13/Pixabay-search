package com.nihilus13.pixabay.fragment.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nihilus13.imageloader.ImageLoaderManager
import com.nihilus13.scaffold.adapter.delegate.AbstractViewHolderDelegate
import com.nihilus13.scaffold.adapter.model.RecyclerItem
import com.nihilus13.ui.R
import com.nihilus13.ui.databinding.PixabaySearchItemBinding

internal class SearchViewHolderDelegate internal constructor(
    private val imageLoaderManager: ImageLoaderManager,
    private val onSearchRecyclerItemClick: (String) -> Unit,
    bindingInflater: (ViewGroup) -> PixabaySearchItemBinding
) : AbstractViewHolderDelegate<SearchViewHolder, SearchRecyclerItem, PixabaySearchItemBinding>(
    bindingInflater
) {

    constructor(
        imageLoaderManager: ImageLoaderManager,
        onSearchRecyclerItemClick: (String) -> Unit
    ) : this(
        imageLoaderManager,
        onSearchRecyclerItemClick,
        { PixabaySearchItemBinding.inflate(LayoutInflater.from(it.context), it, false) }
    )

    override fun getViewHolderType(): Int = VIEW_TYPE

    override fun createViewHolder(parent: ViewGroup): SearchViewHolder =
        SearchViewHolder(
            imageLoaderManager = imageLoaderManager,
            onSearchRecyclerItemClick = onSearchRecyclerItemClick,
            binding = createBinding(parent)
        )

    override fun typeCheck(recyclerItem: RecyclerItem): Boolean =
        recyclerItem is SearchRecyclerItem

    companion object {
        const val VIEW_TYPE: Int = R.layout.pixabay_search_item
    }
}
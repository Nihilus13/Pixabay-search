package com.nihilus13.pixabay.fragment.search.adapter

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.imageloader.ImageLoaderManager
import com.nihilus13.scaffold.adapter.holder.AbstractViewHolder
import com.nihilus13.ui.databinding.PixabaySearchItemBinding

internal class SearchViewHolder(
    private val imageLoaderManager: ImageLoaderManager,
    private val onSearchRecyclerItemClick: (String) -> Unit,
    private val binding: PixabaySearchItemBinding
) : AbstractViewHolder<SearchRecyclerItem>(binding.root) {

    override fun bind(item: SearchRecyclerItem) {
        bindSearchResult(item.searchResult)
    }

    private fun bindSearchResult(result: SearchResult) = with(binding) {
        container.setOnClickListener { onSearchRecyclerItemClick(result.id.toString()) }
        imageLoaderManager.loadImage(thumbnailImage, result.thumbnailUrl)
        usernameText.text = result.user
        tagsText.text = result.tags.joinToString(",")
    }

    override fun onPayload(payloads: List<Any>) {
        val payload = payloads.first() as SearchRecyclerItem.Payload
        with(payload) {
            searchResult?.let(::bindSearchResult)
        }
    }
}
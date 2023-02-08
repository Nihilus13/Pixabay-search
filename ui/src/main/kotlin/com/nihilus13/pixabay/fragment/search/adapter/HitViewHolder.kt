package com.nihilus13.pixabay.fragment.search.adapter

import com.nihilus13.domain.model.HitData
import com.nihilus13.imageloader.ImageLoaderManager
import com.nihilus13.scaffold.adapter.holder.AbstractViewHolder
import com.nihilus13.ui.databinding.PixabayHitItemBinding

internal class HitViewHolder(
    private val imageLoaderManager: ImageLoaderManager,
    private val onSearchRecyclerItemClick: (String) -> Unit,
    private val binding: PixabayHitItemBinding
) : AbstractViewHolder<HitRecyclerItem>(binding.root) {

    override fun bind(item: HitRecyclerItem) {
        bindSearchResult(item.data)
    }

    private fun bindSearchResult(result: HitData) = with(binding) {
        container.setOnClickListener { onSearchRecyclerItemClick(result.id) }
        imageLoaderManager.loadImage(thumbnailImage, result.thumbnailUrl)
        usernameText.text = result.user
        tagsText.text = result.tags
    }

    override fun onPayload(payloads: List<Any>) {
        val payload = payloads.first() as HitRecyclerItem.Payload
        with(payload) {
            data?.let(::bindSearchResult)
        }
    }
}

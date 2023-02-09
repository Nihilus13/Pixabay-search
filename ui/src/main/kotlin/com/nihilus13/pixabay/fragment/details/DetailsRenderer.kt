package com.nihilus13.pixabay.fragment.details

import androidx.core.view.isVisible
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.imageloader.ImageLoaderManager
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState
import com.nihilus13.ui.databinding.PixabayDetailsFragmentBinding
import javax.inject.Inject

internal class DetailsRenderer @Inject constructor(private val imageLoaderManager: ImageLoaderManager) {

    private lateinit var binding: PixabayDetailsFragmentBinding
    private lateinit var onRefresh: () -> Unit

    fun init(
        binding: PixabayDetailsFragmentBinding,
        onRefresh: () -> Unit
    ) {
        this.binding = binding
        this.onRefresh = onRefresh

        this.binding.refreshButton.setOnClickListener { onRefresh() }
        this.binding.error.setOnClickListener { onRefresh() }
    }

    fun renderState(viewState: DetailsViewState) {
        when (viewState) {
            is DetailsViewState.Data -> renderData(viewState)
            is DetailsViewState.Pending -> renderPending()
            is DetailsViewState.Error -> renderNoData()
        }
    }

    private fun renderData(viewState: DetailsViewState.Data) {
        with(binding) {
            container.isVisible = true
            error.isVisible = false
            progressIndicator.isVisible = false
        }
        renderDetailsResult(viewState.result)
    }

    private fun renderDetailsResult(result: DetailsResult) = when (result) {
        is DetailsResult.Data -> renderDataResult(result.data)
        DetailsResult.NoData -> renderNoData()
    }

    private fun renderDataResult(data: HitData) {
        imageLoaderManager.loadImage(binding.image, data.largeImageUrl)
        with(binding) {
            usernameText.text = data.user
            tagsText.text = data.tags
            commentsText.text = data.comments.toString()
            likesText.text = data.likes.toString()
            downloadsText.text = data.downloads.toString()
        }
    }

    private fun renderNoData() {
        with(binding) {
            container.isVisible = false
            error.isVisible = true
            progressIndicator.isVisible = false
        }
    }

    private fun renderPending() {
        with(binding) {
            container.isVisible = false
            error.isVisible = false
            progressIndicator.isVisible = true
        }
    }
}

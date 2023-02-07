package com.nihilus13.pixabay.fragment.details

import androidx.core.view.isVisible
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

        this.binding.error.setOnClickListener {
            onRefresh()
        }
    }

    fun renderState(viewState: DetailsViewState) {
        when (viewState) {
            is DetailsViewState.Data -> renderData(viewState)
            is DetailsViewState.Error -> renderError(viewState)
            is DetailsViewState.Pending -> renderPending(viewState)
        }
    }

    private fun renderData(viewState: DetailsViewState.Data) {
        binding.container.isVisible = true
        binding.error.isVisible = false
        binding.progressIndicator.isVisible = false
        imageLoaderManager.loadImage(binding.image, viewState.detailsResult.largeImageURL)
        with(binding) {
            usernameText.text = viewState.detailsResult.pixabayUserName
            tagsText.text = viewState.detailsResult.tags.joinToString(", ")
            commentsText.text = viewState.detailsResult.comments.toString()
            likesText.text = viewState.detailsResult.likes.toString()
            downloadsText.text = viewState.detailsResult.downloads.toString()
        }
    }

    private fun renderError(viewState: DetailsViewState.Error) {
        binding.container.isVisible = false
        binding.error.isVisible = true
        binding.progressIndicator.isVisible = false
    }

    private fun renderPending(viewState: DetailsViewState.Pending) {
        binding.container.isVisible = false
        binding.error.isVisible = false
        binding.progressIndicator.isVisible = true
    }
}
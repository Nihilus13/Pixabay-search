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
        imageLoaderManager.loadImage(binding.image, viewState.result.largeImageURL)
        with(binding) {
            usernameText.text = viewState.result.pixabayUserName
            tagsText.text = viewState.result.tags.joinToString(", ")
            commentsText.text = viewState.result.comments.toString()
            likesText.text = viewState.result.likes.toString()
            downloadsText.text = viewState.result.downloads.toString()
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
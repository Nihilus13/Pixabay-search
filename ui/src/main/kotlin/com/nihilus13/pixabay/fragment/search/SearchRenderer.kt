package com.nihilus13.pixabay.fragment.search

import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import com.nihilus13.ui.databinding.PixabaySearchFragmentBinding
import javax.inject.Inject

internal class SearchRenderer @Inject constructor() {

    private lateinit var binding: PixabaySearchFragmentBinding

    fun init(binding: PixabaySearchFragmentBinding) {
        this.binding = binding
    }

    fun renderState(viewState: SearchViewState) {

    }
}
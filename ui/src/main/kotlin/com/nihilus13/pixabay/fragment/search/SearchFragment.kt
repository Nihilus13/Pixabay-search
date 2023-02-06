package com.nihilus13.pixabay.fragment.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import com.nihilus13.pixabay.fragment.search.state.SearchAction
import com.nihilus13.pixabay.fragment.search.state.SearchSideEffect
import com.nihilus13.pixabay.injection.GenericSavedStateViewModelFactory
import com.nihilus13.pixabay.lifecycle.viewBinding
import com.nihilus13.ui.R
import com.nihilus13.ui.databinding.PixabaySearchFragmentBinding
import javax.inject.Inject

internal class SearchFragment : Fragment(R.layout.pixabay_search_fragment) {

    private val binding by viewBinding<PixabaySearchFragmentBinding>()

    @Inject
    lateinit var renderer: SearchRenderer

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    private val viewModel: SearchViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PixabayComponentProvider
            .getPixabayComponent(requireActivity().applicationContext)
            .searchComponent()
            .create()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderer.init(binding = binding)
        subscribeUi()
        savedInstanceState ?: viewModel.sendAction(SearchAction.InitialSearch)
    }

    private fun subscribeUi() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            renderer.renderState(it)
        }
        viewModel.sideEffect.observe(viewLifecycleOwner) {
            handleSideEffect(it)
        }
    }

    private fun handleSideEffect(sideEffect: SearchSideEffect) {
        when (sideEffect) {
            SearchSideEffect.BlankSearchText -> Unit
            is SearchSideEffect.ProceedToDetails -> Unit
        }
    }
}

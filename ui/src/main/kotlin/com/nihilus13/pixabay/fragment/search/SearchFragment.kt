package com.nihilus13.pixabay.fragment.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nihilus13.data.di.component.DataComponentInjector
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import com.nihilus13.pixabay.common.StateObserver
import com.nihilus13.pixabay.fragment.search.state.SearchAction
import com.nihilus13.pixabay.fragment.search.state.SearchSideEffect
import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import com.nihilus13.pixabay.injection.GenericSavedStateViewModelFactory
import com.nihilus13.pixabay.lifecycle.viewBinding
import com.nihilus13.ui.R
import com.nihilus13.ui.databinding.PixabaySearchFragmentBinding
import javax.inject.Inject

internal class SearchFragment : Fragment(R.layout.pixabay_search_fragment),
    StateObserver<SearchViewState> {

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
        with(requireActivity().applicationContext) {
            PixabayComponentProvider
                .getPixabayComponent(this, DataComponentInjector.getDataComponent(this))
                .searchComponent()
                .create()
                .inject(this@SearchFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderer.init(binding = binding,
            onSearchClick = { viewModel.sendAction(SearchAction.Search(it)) },
            onUpdateText = { viewModel.sendAction(SearchAction.UpdateText(it)) },
            onSearchRecyclerItemClick = { viewModel.sendAction(SearchAction.ShowDetails(it)) }
        )
        subscribeUi()
        savedInstanceState ?: viewModel.sendAction(SearchAction.InitialSearch)
    }

    private fun subscribeUi() {
        observeState(this) {
            renderer.renderState(it)
        }
        viewModel.sideEffect.observe(viewLifecycleOwner) {
            handleSideEffect(it)
        }
    }

    override fun bindStateObserver(owner: LifecycleOwner, observer: Observer<SearchViewState>) {
        viewModel.viewState.observe(owner, observer)
    }

    private fun handleSideEffect(sideEffect: SearchSideEffect) {
        when (sideEffect) {
            SearchSideEffect.BlankSearchText -> showBlankSearchWarning()
            is SearchSideEffect.ProceedToDetails -> navigateToDetails(sideEffect.detailsId)
            SearchSideEffect.NoData -> showNoDataInfo()
        }
    }

    private fun showBlankSearchWarning() =
        Toast.makeText(requireContext(), R.string.app_search_blank_text_warning, Toast.LENGTH_SHORT)
            .show()

    private fun showNoDataInfo() =
        Toast.makeText(requireContext(), R.string.app_details_no_data, Toast.LENGTH_SHORT).show()

    private fun navigateToDetails(searchId: String) {
        val direction = SearchFragmentDirections.navigateToDetailsFragment(searchId)
        findNavController().navigate(direction)
    }
}

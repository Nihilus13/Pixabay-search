package com.nihilus13.pixabay.fragment.search.state

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.usecase.SearchForImageResult
import com.nihilus13.pixabay.fragment.search.adapter.SearchRecyclerItem
import javax.inject.Inject

internal class SearchReducer @Inject constructor() {

    fun reducePending(viewState: SearchViewState, isPending: Boolean = true): SearchViewState =
        viewState.copy(isPending = isPending)

    fun reduceResult(viewState: SearchViewState, result: SearchForImageResult): SearchViewState =
        when (result) {
            SearchForImageResult.Error -> viewState
            is SearchForImageResult.Success -> viewState.copy(
                results = result.results.map(),
                isPending = false
            )
        }

    private fun List<SearchResult>.map(): List<SearchRecyclerItem> =
        map { SearchRecyclerItem(it) }

    fun reduceSearchText(viewState: SearchViewState, searchText: String): SearchViewState =
        viewState.copy(searchText = searchText)
}
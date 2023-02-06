package com.nihilus13.pixabay.fragment.search.state

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.pixabay.fragment.search.adapter.SearchRecyclerItem
import javax.inject.Inject

internal class SearchReducer @Inject constructor() {

    fun reducePending(viewState: SearchViewState, isPending: Boolean = true): SearchViewState =
        viewState.copy(isPending = isPending)

    fun reduceResult(viewState: SearchViewState, result: List<SearchResult>): SearchViewState =
        viewState.copy(results = result.map())

    private fun List<SearchResult>.map(): List<SearchRecyclerItem> =
        map { SearchRecyclerItem(it) }
}
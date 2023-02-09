package com.nihilus13.pixabay.fragment.search.state

import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.pixabay.fragment.search.adapter.HitRecyclerItem
import javax.inject.Inject

internal class SearchReducer @Inject constructor() {

    fun reducePending(viewState: SearchViewState, isPending: Boolean = true): SearchViewState =
        viewState.copy(isPending = isPending)

    fun reduceResult(viewState: SearchViewState, result: SearchResult): SearchViewState =
        when (result) {
            SearchResult.NoData -> viewState.copy(isPending = false)
            is SearchResult.Data -> viewState.copy(
                results = result.record.hits.map(),
                isPending = false
            )
        }

    private fun List<HitData>.map(): List<HitRecyclerItem> =
        map { HitRecyclerItem(it) }

    fun reduceSearchText(viewState: SearchViewState, searchText: String): SearchViewState =
        viewState.copy(searchText = searchText)
}

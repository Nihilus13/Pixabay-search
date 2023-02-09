package com.nihilus13.pixabay.fragment.search

import com.nihilus13.domain.model.HitData
import com.nihilus13.pixabay.CommonDataProvider.HIT_LONG_ID
import com.nihilus13.pixabay.CommonDataProvider.TAGS
import com.nihilus13.pixabay.CommonDataProvider.URL
import com.nihilus13.pixabay.CommonDataProvider.USER
import com.nihilus13.pixabay.fragment.search.adapter.HitRecyclerItem
import com.nihilus13.pixabay.fragment.search.state.SearchViewState

internal object SearchDataProvider {

    private const val HIT_ID = HIT_LONG_ID.toString()

    val hitData = HitData(
        id = HIT_ID,
        thumbnailUrl = URL,
        largeImageUrl = URL,
        tags = TAGS,
        user = USER,
        likes = 5L,
        downloads = 3L,
        comments = 10L
    )

    val searchResultsList = listOf(hitData).map { HitRecyclerItem(it) }

    val emptyState = SearchViewState(
        isPending = false,
        searchText = "",
        results = listOf()
    )
    val initialState = SearchViewState(
        isPending = true,
        searchText = "Flowers",
        results = listOf()
    )
    val searchingState = SearchViewState(
        isPending = true,
        searchText = "Flowers",
        results = listOf()
    )

    val listState = SearchViewState(
        isPending = false,
        searchText = "Flowers",
        results = searchResultsList
    )
}

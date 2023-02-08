package com.nihilus13.pixabay.fragment.search

import com.nihilus13.domain.model.HitData
import com.nihilus13.pixabay.fragment.search.adapter.HitRecyclerItem
import com.nihilus13.pixabay.fragment.search.state.SearchViewState

internal object SearchDataProvider {

    private const val HIT_LONG_ID = 1L
    private const val HIT_ID = HIT_LONG_ID.toString()
    val hitData = HitData(
        id = HIT_ID,
        thumbnailUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        largeImageUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        tags = "awesome, beauty, sun",
        user = "Hugo",
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

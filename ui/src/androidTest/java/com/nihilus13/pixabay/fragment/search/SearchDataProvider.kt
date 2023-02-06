package com.nihilus13.pixabay.fragment.search

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.pixabay.fragment.search.adapter.SearchRecyclerItem
import com.nihilus13.pixabay.fragment.search.state.SearchViewState

internal object SearchDataProvider {

    val searchResult1 = SearchResult(
        id = 1L,
        thumbnailUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        pixabayUserName = "Hugo",
        tags = listOf("awesome", "beauty", "sun"),
        user = "Hugo",
        downloads = 3L,
        likes = 5L,
        comments = 10L,
    )

    val searchResultsList = listOf(searchResult1).map { SearchRecyclerItem(it) }

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
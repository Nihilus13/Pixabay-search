package com.nihilus13.pixabay.fragment

import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.pixabay.fragment.search.adapter.SearchRecyclerItem

internal object TestDataProvider {

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

    val detailsResult = DetailsResult(
        id = "1L",
        largeImageURL = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        pixabayUserName = "Hugo",
        tags = listOf("awesome", "beauty", "sun"),
        downloads = 3L,
        likes = 5L,
        comments = 10L,
    )
}
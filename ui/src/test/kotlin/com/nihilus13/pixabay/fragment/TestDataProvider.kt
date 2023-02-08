package com.nihilus13.pixabay.fragment

import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.pixabay.fragment.search.adapter.HitRecyclerItem

internal object TestDataProvider {

    const val SEARCH_TEXT: String = "flower"
    private const val HIT_LONG_ID = 1L
    private const val HIT_ID = HIT_LONG_ID.toString()
    private const val TOTAL = 1L

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
    val searchRecord = SearchRecord(
        searchText = SEARCH_TEXT,
        total = TOTAL,
        totalHits = TOTAL,
        hits = listOf(hitData)
    )

    val searchResult = SearchResult.Data(searchRecord)
    val searchResultNoData = SearchResult.NoData
    val searchResultsList = listOf(searchResult).map { HitRecyclerItem(hitData) }
    val detailsResult = DetailsResult.Data(hitData)
    val detailsResultNoData = DetailsResult.NoData
    val hitRecyclerItem = HitRecyclerItem(hitData)
}
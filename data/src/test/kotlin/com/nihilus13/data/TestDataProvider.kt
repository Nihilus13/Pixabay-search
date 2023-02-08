package com.nihilus13.data

import com.nihilus13.data.db.entity.HitEntity
import com.nihilus13.data.db.entity.SearchRecordEntity
import com.nihilus13.data.db.entity.SearchRecordWithHits
import com.nihilus13.data.model.Hit
import com.nihilus13.data.model.SearchResponse
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord

internal object TestDataProvider {

    const val SEARCH_TEXT: String = "flower"
    const val HIT_LONG_ID = 1L
    const val TOTAL = 1L
    const val HIT_ID = HIT_LONG_ID.toString()
    const val CREATED_AT_TIMESTAMP = 167584000L
    val hit = Hit(
        id = HIT_LONG_ID,
        previewURL = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        tags = "awesome, beauty, sun",
        largeImageURL = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        user = "Hugo",
        downloads = 3L,
        likes = 5L,
        comments = 10L
    )
    val response = SearchResponse(total = 1, totalHits = 1, hits = listOf(hit))

    val hitEntity = HitEntity(
        id = HIT_ID,
        createdAt = CREATED_AT_TIMESTAMP,
        thumbnailUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        largeImageUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        tags = "awesome, beauty, sun",
        user = "Hugo",
        downloads = 3L,
        likes = 5L,
        comments = 10L
    )
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
    val searchRecordEntity = SearchRecordEntity(
        searchText = SEARCH_TEXT,
        createdAt = CREATED_AT_TIMESTAMP,
        total = TOTAL,
        totalHits = TOTAL
    )
    val searchRecordWithHits = SearchRecordWithHits(
        searchRecord = searchRecordEntity,
        hits = listOf(hitEntity)
    )
    val searchRecord = SearchRecord(
        searchText = SEARCH_TEXT,
        total = TOTAL,
        totalHits = TOTAL,
        hits = listOf(hitData)
    )
}
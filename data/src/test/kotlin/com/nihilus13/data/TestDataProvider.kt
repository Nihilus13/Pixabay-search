package com.nihilus13.data

import com.nihilus13.data.db.entity.HitEntity
import com.nihilus13.data.db.entity.SearchRecordEntity
import com.nihilus13.data.db.entity.SearchRecordHitEntity
import com.nihilus13.data.db.entity.SearchRecordWithHits
import com.nihilus13.data.model.Hit
import com.nihilus13.data.model.SearchResponse
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord

internal object TestDataProvider {

    const val SEARCH_TEXT: String = "flower"
    private const val HIT_LONG_ID = 1L
    private const val TOTAL = 1L
    const val HIT_ID = HIT_LONG_ID.toString()
    const val CREATED_AT_TIMESTAMP = 167584000L
    private const val URL =
        "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png"
    private const val TAGS = "awesome, beauty, sun"
    private const val USER = "Hugo"

    val hit = Hit(
        id = HIT_LONG_ID,
        previewURL = URL,
        tags = TAGS,
        largeImageURL = URL,
        user = USER,
        downloads = 3L,
        likes = 5L,
        comments = 10L
    )
    val response = SearchResponse(total = 1, totalHits = 1, hits = listOf(hit))

    val hitEntity = HitEntity(
        id = HIT_ID,
        createdAt = CREATED_AT_TIMESTAMP,
        thumbnailUrl = URL,
        largeImageUrl = URL,
        tags = TAGS,
        user = USER,
        downloads = 3L,
        likes = 5L,
        comments = 10L
    )
    val hitEntity2 = hitEntity.copy(id = "2")
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
    val searchRecordEntity = SearchRecordEntity(
        searchText = SEARCH_TEXT,
        createdAt = CREATED_AT_TIMESTAMP,
        total = TOTAL,
        totalHits = TOTAL
    )
    val emptySearchRecordWithHits = SearchRecordWithHits(
        searchRecord = searchRecordEntity,
        hits = listOf()
    )
    val searchRecordWithHits = SearchRecordWithHits(
        searchRecord = searchRecordEntity,
        hits = listOf(hitEntity)
    )
    val searchRecordWithHits2 = SearchRecordWithHits(
        searchRecord = searchRecordEntity,
        hits = listOf(hitEntity, hitEntity2)
    )
    val searchRecordHitEntity = SearchRecordHitEntity(
        searchRecordEntity.searchText,
        hitEntity.id
    )

    val searchRecordHitEntities = listOf(
        SearchRecordHitEntity(
            searchRecordEntity.searchText,
            hitEntity.id
        ), SearchRecordHitEntity(
            searchRecordEntity.searchText,
            hitEntity2.id
        )
    )

    val searchRecord = SearchRecord(
        searchText = SEARCH_TEXT,
        total = TOTAL,
        totalHits = TOTAL,
        hits = listOf(hitData)
    )
}
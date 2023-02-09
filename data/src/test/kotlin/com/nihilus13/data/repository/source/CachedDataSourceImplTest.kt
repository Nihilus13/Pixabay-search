package com.nihilus13.data.repository.source

import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.CREATED_AT_TIMESTAMP
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitData
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.TestDataProvider.searchRecord
import com.nihilus13.data.TestDataProvider.searchRecordEntity
import com.nihilus13.data.TestDataProvider.searchRecordHitEntity
import com.nihilus13.data.TestDataProvider.searchRecordWithHits
import com.nihilus13.data.date.DateSource
import com.nihilus13.data.db.dao.HitDao
import com.nihilus13.data.db.dao.SearchRecordWithHitsDao
import com.nihilus13.data.repository.mapper.DomainDatabaseMapper
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class CachedDataSourceImplTest {

    private val hitDao: HitDao = mock()
    private val searchRecordWithHitsDao: SearchRecordWithHitsDao = mock()
    private val dateSource: DateSource = mock()
    private val mapper = DomainDatabaseMapper(dateSource)

    private val cachedDataSource = CachedDataSourceImpl(
        hitDao = hitDao,
        searchRecordWithHitsDao = searchRecordWithHitsDao,
        mapper = mapper
    )

    @Before
    fun setup() {
        whenever(dateSource.getCurrentDateTimestamp()).thenReturn(CREATED_AT_TIMESTAMP)
    }

    @Test
    fun `inserts search record`() = runBlocking {
        cachedDataSource.insertHitData(searchRecord)

        verify(hitDao).insertHits(listOf(hitEntity))
        verify(searchRecordWithHitsDao).insertSearchRecord(searchRecordEntity)
        verify(searchRecordWithHitsDao).insertSearchRecordWithHits(listOf(searchRecordHitEntity))
    }

    @Test
    fun `inserts hit data`() = runBlocking {
        cachedDataSource.insertHitData(hitData)

        verify(hitDao).insertHit(hitEntity)
    }

    @Test
    fun `gets cached search record`() = runBlocking {
        whenever(searchRecordWithHitsDao.getSearchRecord(SEARCH_TEXT))
            .thenReturn(searchRecordWithHits)

        val result = cachedDataSource.searchForImages(SEARCH_TEXT)

        assertThat(result).isEqualTo(SearchResult.Data(searchRecord))
    }

    @Test
    fun `gets cached search record with error`() = runBlocking {
        whenever(searchRecordWithHitsDao.getSearchRecord(SEARCH_TEXT))
            .thenReturn(null)

        val result = cachedDataSource.searchForImages(SEARCH_TEXT)

        assertThat(result).isEqualTo(SearchResult.NoData)
    }

    @Test
    fun `gets detailed hit`() = runBlocking {
        whenever(hitDao.getHit(SEARCH_TEXT))
            .thenReturn(hitEntity)

        val result = cachedDataSource.getDetailedHit(SEARCH_TEXT)

        assertThat(result).isEqualTo(DetailsResult.Data(hitData))
    }

    @Test
    fun `gets detailed hit with error`() = runBlocking {
        whenever(hitDao.getHit(SEARCH_TEXT)).thenReturn(null)

        val result = cachedDataSource.getDetailedHit(SEARCH_TEXT)

        assertThat(result).isEqualTo(DetailsResult.NoData)
    }
}
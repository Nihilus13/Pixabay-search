package com.nihilus13.data.repository.mapper

import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.CREATED_AT_TIMESTAMP
import com.nihilus13.data.TestDataProvider.hitData
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.TestDataProvider.searchRecord
import com.nihilus13.data.TestDataProvider.searchRecordWithHits
import com.nihilus13.data.date.DateSource
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class DomainDatabaseMapperTest {

    private val dateSource: DateSource = mock {
        whenever(mock.getCurrentDateTimestamp()).thenReturn(CREATED_AT_TIMESTAMP)
    }
    private val mapper = DomainDatabaseMapper(dateSource)

    @Test
    fun `maps hit entity to domain model`() {
        val result = mapper.mapHitEntity(hitEntity)

        assertThat(result).isEqualTo(hitData)
    }

    @Test
    fun `maps hit entities to domain models`() {
        val result = mapper.mapHitEntities(listOf(hitEntity))

        assertThat(result).isEqualTo(listOf(hitData))
    }

    @Test
    fun `maps domain models to hit entities`() {
        val result = mapper.mapHitData(listOf(hitData))

        assertThat(result).isEqualTo(listOf(hitEntity))
    }

    @Test
    fun `maps domain models to search record`() {
        val result = mapper.mapSearchRecord(searchRecordWithHits)

        assertThat(result).isEqualTo(searchRecord)
    }
}
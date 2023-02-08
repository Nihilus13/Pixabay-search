package com.nihilus13.data.repository.mapper

import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.TestDataProvider.response
import com.nihilus13.data.TestDataProvider.searchRecord
import org.junit.jupiter.api.Test

internal class DomainNetworkMapperTest {

    private val mapper = DomainNetworkMapper()

    @Test
    fun `maps search response to domain hit data model`() {
        val result = mapper.mapSearchResponse(response)

        assertThat(result).isEqualTo(hitEntity)
    }

    @Test
    fun `maps search response to domain search record model`() {
        val result = mapper.mapSearchResponse(SEARCH_TEXT, response)

        assertThat(result).isEqualTo(searchRecord)
    }
}
package com.nihilus13.data.repository.mapper

import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitData
import com.nihilus13.data.TestDataProvider.response
import com.nihilus13.data.TestDataProvider.searchRecord
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import org.junit.Test

internal class DomainNetworkMapperTest {

    private val mapper = DomainNetworkMapper()

    @Test
    fun `maps search response to domain hit data model`() {
        val result = mapper.mapSearchResponse(response)

        assertThat(result).isEqualTo(DetailsResult.Data(hitData))
    }

    @Test
    fun `maps search response to domain search record model`() {
        val result = mapper.mapSearchResponse(SEARCH_TEXT, response)

        assertThat(result).isEqualTo(SearchResult.Data(searchRecord))
    }
}
package com.nihilus13.data.date

import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.CREATED_AT_TIMESTAMP
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Calendar

internal class DateSourceImplTest {

    private val calendar: Calendar = mock {
        whenever(mock.timeInMillis).thenReturn(CREATED_AT_TIMESTAMP)
    }
    private val dateSource = DateSourceImpl(calendar)

    @Test
    fun `returns correct timestamp`() {
        val result = dateSource.getCurrentDateTimestamp()

        assertThat(result).isEqualTo(CREATED_AT_TIMESTAMP)
    }
}
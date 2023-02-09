package com.nihilus13.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.TestDataProvider.searchRecordEntity
import com.nihilus13.data.TestDataProvider.searchRecordWithHits
import com.nihilus13.data.db.PixabayDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SearchRecordWithHitsDaoTest {

    private lateinit var database: PixabayDatabase
    private lateinit var dao: SearchRecordWithHitsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixabayDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.searchRecordWithHitsDao()
    }

    @Test
    fun `inserts search record with `() = runBlocking {
        dao.insert(searchRecordEntity, listOf(hitEntity))

        val searchRecord = dao.getSearchRecord(SEARCH_TEXT)

        assertThat(searchRecord).isEqualTo(searchRecordWithHits)
    }

    @After
    fun release() {
        database.close()
    }
}
package com.nihilus13.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.emptySearchRecordWithHits
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.TestDataProvider.hitEntity2
import com.nihilus13.data.TestDataProvider.searchRecordEntity
import com.nihilus13.data.TestDataProvider.searchRecordHitEntities
import com.nihilus13.data.TestDataProvider.searchRecordHitEntity
import com.nihilus13.data.TestDataProvider.searchRecordWithHits
import com.nihilus13.data.TestDataProvider.searchRecordWithHits2
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
    private lateinit var hitDao: HitDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixabayDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.searchRecordWithHitsDao()
        hitDao = database.hitDao()
    }

    @Test
    fun `inserts search record without hits`() = runBlocking {
        database.clearAllTables()
        dao.insertSearchRecord(searchRecordEntity)

        val searchRecord = dao.getSearchRecord(SEARCH_TEXT)

        assertThat(searchRecord).isEqualTo(emptySearchRecordWithHits)
    }

    @Test
    fun `inserts search record with hit`() = runBlocking {
        database.clearAllTables()
        hitDao.insertHit(hitEntity)
        with(dao) {
            insertSearchRecord(searchRecordEntity)
            insertSearchRecordWithHits(listOf(searchRecordHitEntity))
        }
        val searchRecord = dao.getSearchRecord(SEARCH_TEXT)

        assertThat(searchRecord).isEqualTo(searchRecordWithHits)
    }

    @Test
    fun `inserts search record with hit with uppercase`() = runBlocking {
        database.clearAllTables()
        hitDao.insertHit(hitEntity)
        with(dao) {
            insertSearchRecord(searchRecordEntity)
            insertSearchRecordWithHits(listOf(searchRecordHitEntity))
        }
        val searchRecord = dao.getSearchRecord(SEARCH_TEXT.uppercase())

        assertThat(searchRecord).isEqualTo(searchRecordWithHits)
    }

    @Test
    fun `inserts search record with hits`() = runBlocking {
        database.clearAllTables()
        hitDao.insertHits(listOf(hitEntity, hitEntity2))
        with(dao) {
            insertSearchRecord(searchRecordEntity)
            insertSearchRecordWithHits(searchRecordHitEntities)
        }
        val searchRecord = dao.getSearchRecord(SEARCH_TEXT)

        assertThat(searchRecord).isEqualTo(searchRecordWithHits2)
    }

    @After
    fun release() {
        database.close()
    }
}
package com.nihilus13.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.HIT_ID
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.TestDataProvider.hitEntity2
import com.nihilus13.data.db.PixabayDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class HitDaoTest {

    private lateinit var database: PixabayDatabase
    private lateinit var dao: HitDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixabayDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.hitDao()
    }

    @Test
    fun `fetches hits with given id`() = runBlocking {
        database.clearAllTables()
        dao.insertHits(listOf(hitEntity, hitEntity2))

        val hit = dao.getHits()

        assertThat(hit).isEqualTo(listOf(hitEntity, hitEntity2))
    }

    @Test
    fun `fetches hit with given id`() = runBlocking {
        database.clearAllTables()
        dao.insertHit(hitEntity)

        val hit = dao.getHit(HIT_ID)

        assertThat(hit).isEqualTo(hitEntity)
    }

    @After
    fun teardown() {
        database.close()
    }
}
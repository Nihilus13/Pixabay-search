package com.nihilus13.data.db.dao

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.TestDataProvider.HIT_ID
import com.nihilus13.data.TestDataProvider.hitEntity
import com.nihilus13.data.db.PixabayDatabase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class HitDaoTest {

    private lateinit var database: PixabayDatabase
    private lateinit var hitDao: HitDao

    @BeforeAll
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixabayDatabase::class.java
        ).allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    runBlocking { hitDao.insertHits(listOf(hitEntity)) }
                }
            }).build()

        hitDao = database.hitDao()
    }

    @Test
    fun `fetches hit with given id`() = runBlocking {
        val hitEntity = hitDao.getHit(HIT_ID)
        assertThat(hitEntity).isNotNull()
    }

    @AfterAll
    fun release() {
        database.close()
    }
}
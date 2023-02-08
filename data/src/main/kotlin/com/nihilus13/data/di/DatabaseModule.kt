package com.nihilus13.data.di

import android.content.Context
import com.nihilus13.data.db.PixabayDatabase
import com.nihilus13.data.db.PixabayDatabaseProvider
import com.nihilus13.data.db.dao.HitDao
import com.nihilus13.data.db.dao.SearchRecordWithHitsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal interface DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): PixabayDatabase =
            PixabayDatabaseProvider.getDatabase(context)

        @Provides
        fun provideHitDao(database: PixabayDatabase): HitDao = database.hitDao()


        @Provides
        fun provideSearchRecordWithDao(database: PixabayDatabase): SearchRecordWithHitsDao =
            database.searchRecordWithHitsDao()
    }
}
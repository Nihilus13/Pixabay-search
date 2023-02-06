package com.nihilus13.data.di

import com.nihilus13.data.repository.SearchRepositoryImpl
import com.nihilus13.data.repository.source.CachedDataSourceImpl
import com.nihilus13.data.repository.source.RemoteDataSourceImpl
import com.nihilus13.domain.repository.SearchRepository
import com.nihilus13.domain.repository.source.CachedDataSource
import com.nihilus13.domain.repository.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
internal object RepositoryModule {

    @Provides
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource =
        remoteDataSource

    @Provides
    fun provideCachedDataSource(cachedDataSource: CachedDataSourceImpl): CachedDataSource =
        cachedDataSource

    @Provides
    fun provideSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository =
        searchRepository
}

package com.nihilus13.data.di

import com.nihilus13.data.repository.PixabayRepositoryImpl
import com.nihilus13.data.repository.source.CachedDataSourceImpl
import com.nihilus13.data.repository.source.RemoteDataSourceImpl
import com.nihilus13.domain.repository.PixabayRepository
import com.nihilus13.domain.repository.source.CachedDataSource
import com.nihilus13.domain.repository.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
internal object RepositoryModule {

    @Provides
    internal fun provideRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource =
        remoteDataSource

    @Provides
    internal fun provideCachedDataSource(cachedDataSource: CachedDataSourceImpl): CachedDataSource =
        cachedDataSource

    @Provides
    fun providePixabayRepository(searchRepository: PixabayRepositoryImpl): PixabayRepository =
        searchRepository
}

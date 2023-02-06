package com.nihilus13.data.di

import com.nihilus13.data.usecase.LoadDetailsUseCaseImpl
import com.nihilus13.data.usecase.SearchUseCaseImpl
import com.nihilus13.domain.usecase.LoadDetailsUseCase
import com.nihilus13.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides

@Module
interface UseCaseModule {

    companion object {
        @Provides
        internal fun provideSearchPixabayUseCase(searchPixabayUseCase: SearchUseCaseImpl): SearchUseCase =
            searchPixabayUseCase

        @Provides
        internal fun provideLoadDetailsUseCase(loadDetailsUseCase: LoadDetailsUseCaseImpl): LoadDetailsUseCase =
            loadDetailsUseCase
    }
}

package com.nihilus13.data.di

import com.nihilus13.data.usecase.LoadDetailsUseCaseImpl
import com.nihilus13.data.usecase.SearchPixabayUseCaseImpl
import com.nihilus13.domain.usecase.LoadDetailsUseCase
import com.nihilus13.domain.usecase.SearchPixabayUseCase
import dagger.Module
import dagger.Provides

@Module
interface UseCaseModule {

    companion object {
        @Provides
        internal fun provideSearchPixabayUseCase(searchPixabayUseCase: SearchPixabayUseCaseImpl): SearchPixabayUseCase =
            searchPixabayUseCase

        @Provides
        internal fun provideLoadDetailsUseCase(loadDetailsUseCase: LoadDetailsUseCaseImpl): LoadDetailsUseCase =
            loadDetailsUseCase
    }
}

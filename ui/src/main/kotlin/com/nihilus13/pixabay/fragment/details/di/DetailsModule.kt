package com.nihilus13.pixabay.fragment.details.di

import com.nihilus13.pixabay.fragment.details.state.DetailsViewState
import dagger.Module
import dagger.Provides

@Module
internal interface DetailsModule {

    companion object {
        @Provides
        fun provideInitialState(@DetailsId detailsId: String): DetailsViewState =
            DetailsViewState.Pending(detailsId = detailsId)
    }
}

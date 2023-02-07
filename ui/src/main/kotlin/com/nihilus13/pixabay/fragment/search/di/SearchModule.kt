package com.nihilus13.pixabay.fragment.search.di

import androidx.lifecycle.ViewModel
import com.nihilus13.pixabay.fragment.search.SearchViewModel
import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import com.nihilus13.pixabay.injection.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideViewModel(viewModel: SearchViewModel): ViewModel

    companion object {

        @Provides
        fun provideInitialState(): SearchViewState = SearchViewState(
            isPending = false,
            searchText = "flowers",
            results = listOf()
        )
    }
}

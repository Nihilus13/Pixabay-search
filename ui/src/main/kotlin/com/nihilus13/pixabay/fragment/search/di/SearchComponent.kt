package com.nihilus13.pixabay.fragment.search.di

import com.nihilus13.data.di.UseCaseModule
import com.nihilus13.pixabay.fragment.search.SearchFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [SearchModule::class,
        UseCaseModule::class]
)
internal interface SearchComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    fun inject(fragment: SearchFragment)
}

package com.nihilus13.pixabay.fragment.details.di

import com.nihilus13.data.di.UseCaseModule
import com.nihilus13.pixabay.fragment.details.DetailsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [DetailsModule::class,
        UseCaseModule::class]
)
internal interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(fragment: DetailsFragment)
}

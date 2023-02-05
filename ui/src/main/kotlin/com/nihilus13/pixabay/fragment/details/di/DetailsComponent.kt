package com.nihilus13.pixabay.fragment.details.di

import com.nihilus13.pixabay.fragment.details.DetailsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [DetailsModule::class]
)
internal interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(fragment: DetailsFragment)
}

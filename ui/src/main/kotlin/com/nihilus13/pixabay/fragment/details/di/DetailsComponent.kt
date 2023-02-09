package com.nihilus13.pixabay.fragment.details.di

import com.nihilus13.pixabay.fragment.details.DetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Qualifier

@Subcomponent(modules = [DetailsModule::class])
internal interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @DetailsId detailsId: String): DetailsComponent
    }

    fun inject(fragment: DetailsFragment)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DetailsId

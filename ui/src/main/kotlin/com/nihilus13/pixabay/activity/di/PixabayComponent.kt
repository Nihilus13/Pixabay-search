package com.nihilus13.pixabay.activity.di

import android.content.Context
import com.nihilus13.pixabay.activity.PixabayMainActivity
import com.nihilus13.pixabay.fragment.details.di.DetailsComponent
import com.nihilus13.pixabay.fragment.search.di.SearchComponent
import com.nihilus13.pixabay.injection.di.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        PixabayModule::class,
        ViewModelBuilderModule::class,
    ]
)
internal interface PixabayComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): PixabayComponent
    }

    fun searchComponent(): SearchComponent.Factory

    fun detailsComponent(): DetailsComponent.Factory

    fun inject(activity: PixabayMainActivity)
}

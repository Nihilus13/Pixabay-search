package com.nihilus13.pixabay.activity.di

import androidx.lifecycle.ViewModel
import com.nihilus13.pixabay.activity.PixabayViewModel
import com.nihilus13.pixabay.injection.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface PixabayModule {

    @Binds
    @IntoMap
    @ViewModelKey(PixabayViewModel::class)
    fun provideViewModel(viewModel: PixabayViewModel): ViewModel
}

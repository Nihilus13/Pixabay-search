package com.nihilus13.pixabay.activity.di

import androidx.lifecycle.ViewModel
import com.nihilus13.pixabay.activity.PixabayViewModel
import com.nihilus13.pixabay.injection.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
internal interface PixabayModule {

    @Binds
    @IntoMap
    @ViewModelKey(PixabayViewModel::class)
    fun provideViewModel(viewModel: PixabayViewModel): ViewModel

    companion object {

        @Provides
        fun proviceDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}

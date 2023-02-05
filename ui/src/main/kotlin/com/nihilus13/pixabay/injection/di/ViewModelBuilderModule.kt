package com.nihilus13.pixabay.injection.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nihilus13.pixabay.injection.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@Module
interface ViewModelBuilderModule {

    @Binds
    fun bindViewModeFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val values: KClass<out ViewModel>)

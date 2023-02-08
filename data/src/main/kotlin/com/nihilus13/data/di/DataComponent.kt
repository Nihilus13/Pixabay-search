package com.nihilus13.data.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UtilsModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class]
)
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): DataComponent
    }

    fun inject(application: Application)
}

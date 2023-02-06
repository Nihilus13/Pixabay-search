package com.nihilus13.data.di.componenet

import android.app.Application
import android.content.Context
import com.nihilus13.data.di.DatabaseModule
import com.nihilus13.data.di.NetworkModule
import com.nihilus13.data.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
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

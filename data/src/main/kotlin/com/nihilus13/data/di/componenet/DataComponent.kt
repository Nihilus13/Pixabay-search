package com.nihilus13.data.di.componenet

import android.content.Context
import com.nihilus13.data.di.DatabaseModule
import com.nihilus13.data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class]
)
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): DataComponent
    }

    fun inject(application: DataApplication)
}

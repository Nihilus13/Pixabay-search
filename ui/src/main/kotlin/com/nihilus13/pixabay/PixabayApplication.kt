package com.nihilus13.pixabay

import android.app.Application
import com.nihilus13.data.di.component.DaggerDataComponent
import com.nihilus13.data.di.component.DataComponent
import com.nihilus13.data.di.component.DataComponentProvider

class PixabayApplication : Application(), DataComponentProvider {

    private lateinit var dataComponent: DataComponent
    override fun onCreate() {
        super.onCreate()

        dataComponent = DaggerDataComponent.factory().create(this)
    }

    override fun provideDataComponent(): DataComponent = dataComponent
}

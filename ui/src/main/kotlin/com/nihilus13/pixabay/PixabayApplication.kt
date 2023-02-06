package com.nihilus13.pixabay

import android.app.Application
import com.nihilus13.data.di.componenet.DaggerDataComponent
import com.nihilus13.data.di.componenet.DataApplication

class PixabayApplication : Application(), DataApplication {

    override fun onCreate() {
        super.onCreate()
        DaggerDataComponent
            .factory()
            .create(applicationContext)
            .inject(this)
    }
}
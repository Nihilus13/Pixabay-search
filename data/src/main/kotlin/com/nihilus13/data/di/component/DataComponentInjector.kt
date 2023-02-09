package com.nihilus13.data.di.component

import android.content.Context

object DataComponentInjector {

    fun getDataComponent(applicationContext: Context): DataComponent {
        require(applicationContext is DataComponentProvider) {
            "Application context does not implement DataComponentProvider"
        }
        return applicationContext.provideDataComponent()
    }
}

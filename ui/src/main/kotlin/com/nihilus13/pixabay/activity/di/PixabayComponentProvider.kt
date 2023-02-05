package com.nihilus13.pixabay.activity.di

import android.app.Application
import android.content.Context

internal object PixabayComponentProvider {

    private var component: PixabayComponent? = null

    fun getPixabayComponent(context: Context): PixabayComponent {
        require(context is Application) {
            "Requires context as an Application"
        }

        if (component == null) {
            component = DaggerPixabayComponent.factory()
                .create(context)
        }
        return (checkNotNull(component))
    }

    fun clear() {
        component = null
    }
}

package com.nihilus13.pixabay.activity.di

import android.app.Application
import android.content.Context
import com.nihilus13.data.di.component.DataComponent

internal object PixabayComponentProvider {

    private var component: PixabayComponent? = null

    fun getPixabayComponent(context: Context, dataComponent: DataComponent): PixabayComponent {
        require(context is Application) {
            "Requires context as an Application"
        }
        if (component == null) {
            component = DaggerPixabayComponent.factory()
                .create(context, dataComponent)
        }
        return (checkNotNull(component))
    }

    fun clear() {
        component = null
    }
}

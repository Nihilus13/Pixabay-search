package com.nihilus13.pixabay.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nihilus13.data.di.component.DataComponentInjector.getDataComponent
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import com.nihilus13.ui.R

class PixabayMainActivity : AppCompatActivity(R.layout.pixabay_main_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(applicationContext) {
            PixabayComponentProvider
                .getPixabayComponent(this, getDataComponent(this))
                .inject(this@PixabayMainActivity)
        }
    }
}

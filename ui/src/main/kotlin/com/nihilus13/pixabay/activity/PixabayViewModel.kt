package com.nihilus13.pixabay.activity

import androidx.lifecycle.ViewModel
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import javax.inject.Inject

internal class PixabayViewModel @Inject constructor() : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        PixabayComponentProvider.clear()
    }
}

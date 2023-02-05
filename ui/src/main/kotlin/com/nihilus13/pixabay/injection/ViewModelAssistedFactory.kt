package com.nihilus13.pixabay.injection

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

interface ViewModelAssistedFactory<VIEW_MODEL : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): VIEW_MODEL
}

class GenericSavedStateViewModelFactory<out VIEW_MODEL : ViewModel>(
    private val viewModelFactory: ViewModelAssistedFactory<VIEW_MODEL>,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <VIEW_MODEL : ViewModel> create(
        key: String,
        modelClass: Class<VIEW_MODEL>,
        handle: SavedStateHandle
    ): VIEW_MODEL = viewModelFactory.create(handle) as VIEW_MODEL
}
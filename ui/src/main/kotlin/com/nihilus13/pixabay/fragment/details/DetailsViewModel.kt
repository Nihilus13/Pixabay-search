package com.nihilus13.pixabay.fragment.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nihilus13.domain.usecase.LoadDetailsUseCase
import com.nihilus13.pixabay.fragment.details.state.DetailsAction
import com.nihilus13.pixabay.fragment.details.state.DetailsReducer
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState
import com.nihilus13.pixabay.injection.ViewModelAssistedFactory
import com.nihilus13.pixabay.lifecycle.getSavedStateLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressWarnings("ConstructorParameterNaming")
internal class DetailsViewModel internal constructor(
    private val _viewState: MutableLiveData<DetailsViewState>,
    private val loadDetailsUseCase: LoadDetailsUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: DetailsReducer
) : ViewModel() {

    val viewState: LiveData<DetailsViewState>
        get() = _viewState

    fun sendAction(action: DetailsAction) {
        when (action) {
            DetailsAction.InitialLoad -> onInitialLoad()
        }
    }

    private fun onInitialLoad() = viewModelScope.launch {
        reduce { reducer.reducePending(_viewState.value!!) }
        val details = loadDetailsUseCase.loadDetails(_viewState.value!!.detailsId)
        reduce { reducer.reduceDetails(_viewState.value!!, details) }
    }

    private fun reduce(reducer: suspend DetailsViewState.() -> DetailsViewState) =
        viewModelScope.launch(dispatcher) {
            val state = _viewState.value!!.reducer()
            _viewState.postValue(state)
        }
}

internal class DetailsViewModelFactory @Inject constructor(
    private val initialState: DetailsViewState,
    private val searchUseCase: LoadDetailsUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: DetailsReducer
) : ViewModelAssistedFactory<DetailsViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): DetailsViewModel =
        DetailsViewModel(
            _viewState = savedStateHandle.getSavedStateLiveData(initialState),
            loadDetailsUseCase = searchUseCase,
            dispatcher = dispatcher,
            reducer = reducer
        )
}

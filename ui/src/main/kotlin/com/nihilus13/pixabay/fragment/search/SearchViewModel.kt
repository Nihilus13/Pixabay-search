package com.nihilus13.pixabay.fragment.search

import androidx.lifecycle.*
import com.nihilus13.domain.usecase.SearchForImageResult
import com.nihilus13.domain.usecase.SearchUseCase
import com.nihilus13.pixabay.fragment.search.state.SearchAction
import com.nihilus13.pixabay.fragment.search.state.SearchReducer
import com.nihilus13.pixabay.fragment.search.state.SearchSideEffect
import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import com.nihilus13.pixabay.injection.ViewModelAssistedFactory
import com.nihilus13.pixabay.lifecycle.SingleLiveEvent
import com.nihilus13.pixabay.lifecycle.getSavedStateLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SearchViewModel internal constructor(
    private val _viewState: MutableLiveData<SearchViewState>,
    private val searchUseCase: SearchUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: SearchReducer
) : ViewModel() {

    val viewState: LiveData<SearchViewState>
        get() = _viewState

    val sideEffect = SingleLiveEvent<SearchSideEffect>()

    fun sendAction(action: SearchAction) {
        when (action) {
            is SearchAction.Search -> onSearch(action.searchText)
            SearchAction.InitialSearch -> onSearch(_viewState.value!!.searchText)
            is SearchAction.ShowDetails -> onShowDetails(action.detailsId)
            is SearchAction.UpdateText -> onUpdateText(action.updatedText)
        }
    }

    private fun onSearch(searchText: String) = viewModelScope.launch {
        if (searchText.isBlank()) {
            sideEffect.value = SearchSideEffect.BlankSearchText
        } else {
            onSearchInternal(searchText)
        }
    }

    private fun onSearchInternal(searchText: String) = viewModelScope.launch {
        reduce { reducer.reducePending(_viewState.value!!) }
        val result = searchUseCase.searchForImages(searchText)
        if (result is SearchForImageResult.Error) {
            sideEffect.value = SearchSideEffect.SearchError
        }
        reduce { reducer.reduceResult(_viewState.value!!, result) }
    }

    private fun onShowDetails(detailsId: String) {
        sideEffect.value = SearchSideEffect.ProceedToDetails(detailsId)
    }

    private fun onUpdateText(updatedText: String) {
        reduce { reducer.reduceSearchText(_viewState.value!!, updatedText) }
    }

    private fun reduce(reducer: suspend SearchViewState.() -> SearchViewState) =
        viewModelScope.launch(dispatcher) {
            val state = _viewState.value!!.reducer()
            _viewState.postValue(state)
        }
}

internal class SearchViewModelFactory @Inject constructor(
    private val initialState: SearchViewState,
    private val searchUseCase: SearchUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: SearchReducer
) : ViewModelAssistedFactory<SearchViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): SearchViewModel =
        SearchViewModel(
            _viewState = savedStateHandle.getSavedStateLiveData(initialState),
            searchUseCase = searchUseCase,
            dispatcher = dispatcher,
            reducer = reducer
        )
}
package com.nihilus13.pixabay.fragment.search

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.nihilus13.domain.usecase.SearchUseCase
import com.nihilus13.pixabay.fragment.search.TestDataProvider.searchResult1
import com.nihilus13.pixabay.fragment.search.TestDataProvider.searchResultsList
import com.nihilus13.pixabay.fragment.search.adapter.SearchRecyclerItem
import com.nihilus13.pixabay.fragment.search.state.SearchAction
import com.nihilus13.pixabay.fragment.search.state.SearchReducer
import com.nihilus13.pixabay.fragment.search.state.SearchSideEffect
import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import com.nihilus13.testing.CoroutineExtension
import com.nihilus13.testing.InstantTaskExecutorExtension
import com.nihilus13.testing.observeValues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExtendWith(
    value = [CoroutineExtension::class, InstantTaskExecutorExtension::class]
)
internal class SearchViewModelTest {

    private val initialState = SearchViewState(
        isPending = false,
        searchText = "Flowers",
        results = listOf()
    )
    private val searchUseCaseMock: SearchUseCase = mock()
    private val reducer = SearchReducer()

    @Nested
    inner class InitialSearch {

        @Test
        fun `searches on initial state`() {
            given(runBlocking { searchUseCaseMock.searchForImages(initialState.searchText) })
                .willReturn(listOf(searchResult1))
            val viewModel = viewModel()
            val states = viewModel.viewState.observeValues()

            viewModel.sendAction(SearchAction.InitialSearch)

            assertThat(states).isEqualTo(
                listOf(
                    initialState,
                    initialState.copy(isPending = true),
                    initialState.copy(results = listOf(SearchRecyclerItem(searchResult1)))
                )
            )
        }

        @Test
        fun `searches on initial state search text empty`() {
            val emptyInitialState = initialState.copy(searchText = "")
            given(runBlocking { searchUseCaseMock.searchForImages(initialState.searchText) })
                .willReturn(listOf(searchResult1))
            val viewModel = viewModel(emptyInitialState)
            val states = observeValues(viewModel.viewState, viewModel.sideEffect)

            viewModel.sendAction(SearchAction.InitialSearch)

            assertThat(states).isEqualTo(
                listOf(
                    emptyInitialState,
                    SearchSideEffect.BlankSearchText
                )
            )
        }
    }

    @Nested
    inner class Search {

        @Test
        fun `searches for result`() {
            val searchText = "sheep"
            given(runBlocking { searchUseCaseMock.searchForImages(searchText) })
                .willReturn(listOf(searchResult1))
            val viewModel = viewModel(initialState.copy(searchText = searchText))
            val states = viewModel.viewState.observeValues()

            viewModel.sendAction(SearchAction.Search(searchText))

            assertThat(states).isEqualTo(
                listOf(
                    initialState.copy(searchText = searchText),
                    initialState.copy(
                        searchText = searchText,
                        isPending = true
                    ),
                    initialState.copy(
                        searchText = searchText,
                        results = listOf(SearchRecyclerItem(searchResult1))
                    )
                )
            )
        }

        @Test
        fun `searches for result with search text empty`() {
            val searchText = ""
            given(runBlocking { searchUseCaseMock.searchForImages(searchText) })
                .willReturn(listOf(searchResult1))
            val viewModel = viewModel()
            val states = observeValues(viewModel.viewState, viewModel.sideEffect)

            viewModel.sendAction(SearchAction.Search(searchText))

            assertThat(states).isEqualTo(
                listOf(
                    initialState,
                    SearchSideEffect.BlankSearchText
                )
            )
        }
    }

    @Nested
    inner class ShowDetails {

        @Test
        fun `shows details of search result`() {
            val viewModel = viewModel(initialState.copy(results = searchResultsList))
            val states = observeValues(viewModel.viewState, viewModel.sideEffect)

            viewModel.sendAction(SearchAction.ShowDetails(1L.toString()))

            assertThat(states).isEqualTo(
                listOf(
                    initialState.copy(results = searchResultsList),
                    SearchSideEffect.ProceedToDetails(1L.toString())
                )
            )
        }
    }

    @Nested
    inner class UpdateText {

        @Test
        fun `updates text of search input`() {
            val updatedText = "${initialState.searchText} red"
            val viewModel = viewModel()
            val states = viewModel.viewState.observeValues()

            viewModel.sendAction(SearchAction.UpdateText(updatedText))

            assertThat(states).isEqualTo(
                listOf(
                    initialState,
                    initialState.copy(searchText = updatedText)
                )
            )
        }
    }

    private fun viewModel(state: SearchViewState = initialState): SearchViewModel =
        SearchViewModel(
            _viewState = MutableLiveData(state),
            searchUseCase = searchUseCaseMock,
            dispatcher = Dispatchers.Main,
            reducer = reducer
        )
}
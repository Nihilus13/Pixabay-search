package com.nihilus13.pixabay.fragment.details

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.nihilus13.domain.usecase.LoadDetailsUseCase
import com.nihilus13.pixabay.fragment.TestDataProvider.detailsResult
import com.nihilus13.pixabay.fragment.TestDataProvider.detailsResultNoData
import com.nihilus13.pixabay.fragment.details.state.DetailsAction
import com.nihilus13.pixabay.fragment.details.state.DetailsReducer
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState
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

@ExtendWith(value = [CoroutineExtension::class, InstantTaskExecutorExtension::class])
internal class DetailsViewModelTest {

    private val initialState = DetailsViewState.Pending(DETAILS_ID)
    private val loadDetailsUseCase: LoadDetailsUseCase = mock()
    private val reducer = DetailsReducer()

    @Nested
    inner class InitialLoad {

        @Test
        fun `initial state`() {
            val viewModel = viewModel()
            val states = viewModel.viewState.observeValues()

            assertThat(states).isEqualTo(listOf(initialState))
        }

        @Test
        fun `initial loads details with success`() {
            given { runBlocking { loadDetailsUseCase.loadDetails(DETAILS_ID) } }
                .willReturn(detailsResult)
            val viewModel = viewModel()
            val states = viewModel.viewState.observeValues()

            viewModel.sendAction(DetailsAction.InitialLoad)

            assertThat(states).isEqualTo(
                listOf(
                    initialState,
                    DetailsViewState.Data(
                        detailsId = DETAILS_ID,
                        result = detailsResult
                    )
                )
            )
        }

        @Test
        fun `initial loads details with error`() {
            given { runBlocking { loadDetailsUseCase.loadDetails(DETAILS_ID) } }
                .willReturn(detailsResultNoData)
            val viewModel = viewModel()
            val states = viewModel.viewState.observeValues()

            viewModel.sendAction(DetailsAction.InitialLoad)

            assertThat(states).isEqualTo(
                listOf(
                    initialState,
                    DetailsViewState.Error(detailsId = DETAILS_ID)
                )
            )
        }
    }

    private fun viewModel(state: DetailsViewState = initialState): DetailsViewModel =
        DetailsViewModel(
            _viewState = MutableLiveData(state),
            loadDetailsUseCase = loadDetailsUseCase,
            dispatcher = Dispatchers.Main,
            reducer = reducer
        )

    private companion object {
        private const val DETAILS_ID: String = "1"
    }
}
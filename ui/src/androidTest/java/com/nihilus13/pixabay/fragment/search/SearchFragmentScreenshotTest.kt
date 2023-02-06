package com.nihilus13.pixabay.fragment.search

import com.karumi.shot.ScreenshotTest
import com.nihilus13.pixabay.core.compareStateScreenshot
import com.nihilus13.pixabay.fragment.search.SearchDataProvider.emptyState
import com.nihilus13.pixabay.fragment.search.SearchDataProvider.initialState
import com.nihilus13.pixabay.fragment.search.SearchDataProvider.listState
import com.nihilus13.pixabay.fragment.search.SearchDataProvider.searchingState
import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import org.junit.Test

internal class SearchFragmentScreenshotTest : ScreenshotTest {

    @Test
    fun screenshotTestWithEmptyState() {
        compareScreenshot(emptyState)
    }

    @Test
    fun screenshotTestWithInitialState() {
        compareScreenshot(initialState)
    }

    @Test
    fun screenshotTestWithSearchinState() {
        compareScreenshot(searchingState)
    }

    @Test
    fun screenshotTestWithListState() {
        compareScreenshot(listState)
    }

    private fun compareScreenshot(searchViewState: SearchViewState) {
        compareStateScreenshot<SearchFragment>(
            state = searchViewState,
            fragmentArgs = null
        )
    }
}
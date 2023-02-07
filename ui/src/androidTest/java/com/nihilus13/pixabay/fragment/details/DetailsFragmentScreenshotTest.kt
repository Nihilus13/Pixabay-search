package com.nihilus13.pixabay.fragment.details

import com.karumi.shot.ScreenshotTest
import com.nihilus13.pixabay.core.compareStateScreenshot
import com.nihilus13.pixabay.fragment.details.DetailsDataProvider.dataState
import com.nihilus13.pixabay.fragment.details.DetailsDataProvider.errorState
import com.nihilus13.pixabay.fragment.details.DetailsDataProvider.pendingState
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState
import org.junit.Test

internal class DetailsFragmentScreenshotTest : ScreenshotTest {

    @Test
    fun screenshotTestWithDataState() {
        compareScreenshot(dataState)
    }

    @Test
    fun screenshotTestWithPendingState() {
        compareScreenshot(pendingState)
    }

    @Test
    fun screenshotTestWithErrorState() {
        compareScreenshot(errorState)
    }

    private fun compareScreenshot(viewState: DetailsViewState) {
        compareStateScreenshot<DetailsFragment>(
            state = viewState,
            fragmentArgs = null
        )
    }
}
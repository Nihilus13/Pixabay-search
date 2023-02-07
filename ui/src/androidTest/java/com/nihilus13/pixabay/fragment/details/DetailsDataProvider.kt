package com.nihilus13.pixabay.fragment.details

import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState

internal object DetailsDataProvider {

    const val DETAILS_ID = "1"

    private val result = DetailsResult(
        id = DETAILS_ID,
        largeImageURL = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        pixabayUserName = "Hugo",
        tags = listOf("awesome", "beauty", "sun"),
        likes = 5L,
        downloads = 3L,
        comments = 10L,
    )

    val dataState = DetailsViewState.Data(
        detailsId = DETAILS_ID,
        result = result
    )
    val pendingState = DetailsViewState.Pending(DETAILS_ID)
    val errorState = DetailsViewState.Error(DETAILS_ID)
}
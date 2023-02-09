package com.nihilus13.pixabay.fragment.details

import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState

internal object DetailsDataProvider {

    const val DETAILS_ID = "1"
    private const val HIT_LONG_ID = 1L
    private const val HIT_ID = HIT_LONG_ID.toString()
    private const val URL =
        "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png"
    private const val TAGS = "awesome, beauty, sun"
    private const val USER = "Hugo"
    private val hitData = HitData(
        id = HIT_ID,
        thumbnailUrl = URL,
        largeImageUrl = URL,
        tags = TAGS,
        user = USER,
        likes = 5L,
        downloads = 3L,
        comments = 10L
    )

    private val result = DetailsResult.Data(data = hitData)

    val dataState = DetailsViewState.Data(
        detailsId = DETAILS_ID,
        result = result
    )
    val dataStateNoData = DetailsViewState.Error(DETAILS_ID)
    val pendingState = DetailsViewState.Pending(DETAILS_ID)
    val errorState = DetailsViewState.Error(DETAILS_ID)
}

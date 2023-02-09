package com.nihilus13.pixabay.fragment.search.state

internal sealed class SearchSideEffect {
    data class ProceedToDetails(val detailsId: String) : SearchSideEffect()
    object BlankSearchText : SearchSideEffect()
    object NoData : SearchSideEffect()
}

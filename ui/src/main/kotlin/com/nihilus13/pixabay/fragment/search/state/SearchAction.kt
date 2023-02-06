package com.nihilus13.pixabay.fragment.search.state

internal sealed class SearchAction {
    object InitialSearch : SearchAction()
    data class Search(val searchText: String) : SearchAction()
}

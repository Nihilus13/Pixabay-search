package com.nihilus13.pixabay.fragment.search.state

internal sealed class SearchAction {
    object InitialSearch : SearchAction()
    data class Search(val searchText: String) : SearchAction()
    data class UpdateText(val updatedText: String) : SearchAction()
    data class ShowDetails(val detailsId: String) : SearchAction()

}

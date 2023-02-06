package com.nihilus13.pixabay.fragment.search.adapter

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.pixabay.fragment.search.adapter.SearchViewHolderDelegate.Companion.VIEW_TYPE
import com.nihilus13.scaffold.adapter.diff.payload
import com.nihilus13.scaffold.adapter.model.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SearchRecyclerItem(val searchResult: SearchResult) : RecyclerItem {

    override val id: String
        get() = searchResult.id.toString()

    override val viewType: Int
        get() = VIEW_TYPE

    override fun calculatePayload(item: RecyclerItem): Any? =
        (item as? SearchRecyclerItem)?.let { other ->
            Payload(searchResult = payload(other.searchResult, searchResult))
        }

    data class Payload(val searchResult: SearchResult?)
}

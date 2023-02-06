package com.nihilus13.pixabay.fragment.search.adapter

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.scaffold.adapter.model.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SearchRecyclerItem(val searchResult: SearchResult) : RecyclerItem {
    override val id: String
        get() = TODO("Not yet implemented")
    override val viewType: Int
        get() = TODO("Not yet implemented")

    override fun calculatePayload(item: RecyclerItem): Any? {
        TODO("Not yet implemented")
    }

}
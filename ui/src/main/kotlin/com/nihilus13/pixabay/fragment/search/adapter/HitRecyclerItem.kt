package com.nihilus13.pixabay.fragment.search.adapter

import com.nihilus13.domain.model.HitData
import com.nihilus13.pixabay.fragment.search.adapter.HitViewHolderDelegate.Companion.VIEW_TYPE
import com.nihilus13.scaffold.adapter.diff.payload
import com.nihilus13.scaffold.adapter.model.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class HitRecyclerItem(val data: HitData) : RecyclerItem {

    override val id: String
        get() = data.id

    override val viewType: Int
        get() = VIEW_TYPE

    override fun calculatePayload(item: RecyclerItem): Any? =
        (item as? HitRecyclerItem)?.let { other ->
            Payload(data = payload(other.data, data))
        }

    data class Payload(val data: HitData?)
}

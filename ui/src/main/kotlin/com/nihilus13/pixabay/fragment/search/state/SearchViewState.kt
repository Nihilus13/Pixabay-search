package com.nihilus13.pixabay.fragment.search.state

import android.os.Parcelable
import com.nihilus13.scaffold.adapter.model.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SearchViewState(
    val isPending: Boolean,
    val searchText: String,
    val results: List<RecyclerItem>
) : Parcelable
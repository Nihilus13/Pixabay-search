package com.nihilus13.pixabay.fragment.search

import android.content.Context
import androidx.fragment.app.Fragment
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import com.nihilus13.ui.R

internal class SearchFragment : Fragment(R.layout.pixabay_search_fragment) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PixabayComponentProvider
            .getPixabayComponent(requireActivity().applicationContext)
            .searchComponent()
            .create()
            .inject(this)
    }
}

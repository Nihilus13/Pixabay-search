package com.nihilus13.pixabay.fragment.details

import android.content.Context
import androidx.fragment.app.Fragment
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import com.nihilus13.ui.R

internal class DetailsFragment : Fragment(R.layout.pixabay_details_fragment) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PixabayComponentProvider
            .getPixabayComponent(requireActivity().applicationContext)
            .detailsComponent()
            .create()
            .inject(this)
    }
}

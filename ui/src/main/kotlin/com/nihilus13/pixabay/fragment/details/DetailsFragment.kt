package com.nihilus13.pixabay.fragment.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.nihilus13.data.di.component.DataComponentInjector.getDataComponent
import com.nihilus13.pixabay.activity.di.PixabayComponentProvider
import com.nihilus13.pixabay.common.StateObserver
import com.nihilus13.pixabay.fragment.details.state.DetailsAction
import com.nihilus13.pixabay.fragment.details.state.DetailsViewState
import com.nihilus13.pixabay.injection.GenericSavedStateViewModelFactory
import com.nihilus13.pixabay.lifecycle.viewBinding
import com.nihilus13.ui.R
import com.nihilus13.ui.databinding.PixabayDetailsFragmentBinding
import javax.inject.Inject

internal class DetailsFragment : Fragment(R.layout.pixabay_details_fragment),
    StateObserver<DetailsViewState> {

    private val binding by viewBinding<PixabayDetailsFragmentBinding>()

    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var renderer: DetailsRenderer

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private val viewModel: DetailsViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this, arguments)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        with(requireActivity().applicationContext) {
            PixabayComponentProvider
                .getPixabayComponent(this, getDataComponent(this))
                .detailsComponent()
                .create(args.detailsId)
                .inject(this@DetailsFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderer.init(
            binding = binding,
            onRefresh = { viewModel.sendAction(DetailsAction.InitialLoad) }
        )
        subscribeUi()
        savedInstanceState ?: viewModel.sendAction(DetailsAction.InitialLoad)
    }

    private fun subscribeUi() {
        observeState(this) {
            renderer.renderState(it)
        }
    }

    override fun bindStateObserver(owner: LifecycleOwner, observer: Observer<DetailsViewState>) {
        viewModel.viewState.observe(owner, observer)
    }
}

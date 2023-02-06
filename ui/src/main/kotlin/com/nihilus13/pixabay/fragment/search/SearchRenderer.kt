package com.nihilus13.pixabay.fragment.search

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.nihilus13.imageloader.ImageLoaderManager
import com.nihilus13.pixabay.extensions.hideKeyboard
import com.nihilus13.pixabay.fragment.search.adapter.SearchViewHolderDelegate
import com.nihilus13.pixabay.fragment.search.state.SearchViewState
import com.nihilus13.scaffold.adapter.CommonAdapter
import com.nihilus13.scaffold.adapter.delegate.DelegateType
import com.nihilus13.ui.R
import com.nihilus13.ui.databinding.PixabaySearchFragmentBinding
import javax.inject.Inject

internal class SearchRenderer @Inject constructor(private val imageLoaderManager: ImageLoaderManager) {

    private lateinit var binding: PixabaySearchFragmentBinding
    private lateinit var onSearchClick: (String) -> Unit
    private lateinit var onUpdateText: (String) -> Unit
    private lateinit var onSearchRecyclerItemClick: (String) -> Unit

    private val delegates by lazy {
        setOf(SearchViewHolderDelegate(imageLoaderManager) { showConfirmationDialog(it) })
    }
    private val commonAdapter by lazy { CommonAdapter(delegates as Set<DelegateType>) }

    private val searchTextWatcher = object : TextWatcher {

        var previousText: String = ""

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            previousText = s.toString()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) = updateText(s.toString())
    }

    private fun updateText(text: String) {
        with(binding.searchEdit) {
            removeTextChangedListener(searchTextWatcher)
            setText(searchTextWatcher.previousText)
            setSelection(searchTextWatcher.previousText.length)
            onUpdateText(text)
            addTextChangedListener(searchTextWatcher)
        }
    }

    fun init(
        binding: PixabaySearchFragmentBinding,
        onSearchClick: (String) -> Unit,
        onUpdateText: (String) -> Unit,
        onSearchRecyclerItemClick: (String) -> Unit
    ) {
        this.binding = binding
        this.onSearchClick = onSearchClick
        this.onUpdateText = onUpdateText
        this.onSearchRecyclerItemClick = onSearchRecyclerItemClick

        binding.searchEdit.addTextChangedListener(searchTextWatcher)
        binding.imagesList.apply {
            adapter = commonAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
        }
    }

    fun renderState(viewState: SearchViewState) {
        with(binding) {
            progressIndicator.isVisible = viewState.isPending
            imagesList.isVisible = !viewState.isPending
        }
        with(binding.searchButton) {
            isEnabled = !viewState.isPending || viewState.searchText.isNotBlank()
            setOnClickListener { onSearchClick(viewState.searchText) }
        }
        bindSearchEdit(viewState.searchText)
    }

    private fun showConfirmationDialog(detailId: String) {
        AlertDialog.Builder(binding.root.context)
            .setMessage(R.string.app_details_confirmation)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                onSearchRecyclerItemClick(detailId)
            }
            .setNegativeButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun bindSearchEdit(searchText: String) {
        with(binding.searchEdit) {
            removeTextChangedListener(searchTextWatcher)
            setText(searchText)
            setSelection(searchText.length)
            addTextChangedListener(searchTextWatcher)
            setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onSearchClick(searchText)
                    view.hideKeyboard()
                }
                true
            }
        }
    }
}

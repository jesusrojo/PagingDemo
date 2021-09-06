package com.jesusrojo.pagingdemo.paging.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.ItemsLayoutBinding
import com.jesusrojo.pagingdemo.utils.DebugHelp
import com.jesusrojo.pagingdemo.utils.InternetHelp
import com.jesusrojo.pagingdemo.utils.hideSoftKeyboard

abstract class BaseFragment : Fragment() {

    protected val DEFAULT_QUERY: String = "Kotlin"
    protected var LAST_SEARCH_QUERY: String? = null
    protected var LAST_SEARCH_QUERY_KEY: String = "LAST_SEARCH_QUERY_KEY"
    protected lateinit var binding: ItemsLayoutBinding
    protected val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemsLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DebugHelp.l("onViewCreated ${javaClass.simpleName}")

        if(!InternetHelp.isNetworkAvailable(requireContext())){
            Toast.makeText(requireContext(),
                requireContext().getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY_KEY, LAST_SEARCH_QUERY)
    }

    protected fun handleMyState(loadState: CombinedLoadStates) {
        DebugHelp.l("handleMyState $loadState")

        //show progress bar when the load state is Loading
        binding.progressBarItems.isVisible =
            loadState.source.refresh is LoadState.Loading

        //load state for error and show the msg on UI
        val errorState = loadState.source.refresh as? LoadState.Error // PagingSource loadstate
            ?: loadState.source.prepend as? LoadState.Error // PagingSource loadstate
            ?: loadState.source.append as? LoadState.Error // PagingSource loadstate
            ?: loadState.refresh as? LoadState.Error // RemoteMediator loadstate
            ?: loadState.append as? LoadState.Error // RemoteMediator loadstate
            ?: loadState.prepend as? LoadState.Error // RemoteMediator loadstate


        errorState?.let {
            val message = it.error.message.toString()
            DebugHelp.l("ERROR $message")
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    // SEARCH
    protected fun initEditText(query: String) {
        binding.editTextSearch.setText(query)

        binding.editTextSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput()
                requireActivity().hideSoftKeyboard()
                true
            } else {
                false
            }
        }
        binding.editTextSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                requireActivity().hideSoftKeyboard()
                true
            } else {
                false
            }
        }

        // Scroll to top when the list is refreshed from network.//todo
//        lifecycleScope.launch {
//            pagingDataAdapter.loadStateFlow
//                // Only emit when REFRESH LoadState for RemoteMediator changes.
//                .distinctUntilChangedBy { it.refresh }
//                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
//                .filter { it.refresh is LoadState.NotLoading }
//                .collect { binding.list.scrollToPosition(0) }
//        }
    }

    private fun updateRepoListFromInput() {
        binding.editTextSearch.text.trim().let {
            if (it.isNotEmpty()) {
                val query = it.toString()
                search(query)
                LAST_SEARCH_QUERY = query
            }
        }
    }

    abstract fun search(query: String)
}
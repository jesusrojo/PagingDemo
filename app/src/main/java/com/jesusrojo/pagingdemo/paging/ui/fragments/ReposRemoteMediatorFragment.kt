package com.jesusrojo.pagingdemo.paging.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.jesusrojo.pagingdemo.paging.ui.adapter.ReposLoadStateAdapter
import com.jesusrojo.pagingdemo.paging.ui.adapter.mediator.ReposUiModelAdapter
import com.jesusrojo.pagingdemo.paging.ui.di.Injection
import com.jesusrojo.pagingdemo.paging.ui.viewmodel.ReposRemoteMediatorViewModel
import com.jesusrojo.pagingdemo.utils.AppHelper
import com.jesusrojo.pagingdemo.utils.DebugHelp
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReposRemoteMediatorFragment: BaseFragment() {

    private lateinit var viewModel: ReposRemoteMediatorViewModel
//    private lateinit var remoteMediatorAdapter: ReposRemoteMediatorDataAdapter
    private lateinit var uiModelAdapter: ReposUiModelAdapter
    private var searchJob: Job? = null

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // INIT ADAPTER
        ////remoteMediatorAdapter = ReposRemoteMediatorDataAdapter()
        uiModelAdapter = ReposUiModelAdapter {
            AppHelper.showAlertDetails(requireActivity() as AppCompatActivity, it.toString())
        }

        // INIT RECYCLER VIEW
        binding.recyclerViewItems.apply {
            layoutManager = linearLayoutManager
            adapter = uiModelAdapter
        }

        binding.recyclerViewItems.adapter =
            uiModelAdapter.withLoadStateHeaderAndFooter(
                    ////header = ReposLoadStateAdapter{ remoteMediatorAdapter.retry() },
                    ////footer = ReposLoadStateAdapter{ remoteMediatorAdapter.retry() }
                      header = ReposLoadStateAdapter{ uiModelAdapter.retry() },
                     footer = ReposLoadStateAdapter{ uiModelAdapter.retry() }
                )

        // ADD LOAD STATE LISTENER TO ADAPTER
        uiModelAdapter.addLoadStateListener { loadState-> handleMyState(loadState) }

        // INIT & OBSERVE VIEW MODEL
        viewModel = Injection.getReposRemoteMediatorViewModel(requireActivity() as AppCompatActivity)

        //SEARCH
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY_KEY) ?: DEFAULT_QUERY
        initEditText(query)
        search(query)
    }

    @ExperimentalPagingApi
    override fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getDatas(query).collectLatest {
                DebugHelp.l("search, query: $query")
                uiModelAdapter.submitData(lifecycle, it)
            }
        }
    }
}
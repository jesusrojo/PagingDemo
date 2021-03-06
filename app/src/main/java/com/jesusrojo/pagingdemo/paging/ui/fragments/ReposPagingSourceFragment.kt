package com.jesusrojo.pagingdemo.paging.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.paging.ui.adapter.ReposLoadStateAdapter
import com.jesusrojo.pagingdemo.paging.ui.adapter.ReposPagingDataAdapter
import com.jesusrojo.pagingdemo.paging.ui.di.Injection
import com.jesusrojo.pagingdemo.paging.ui.viewmodel.ReposViewModel
import com.jesusrojo.pagingdemo.utils.AppHelper
import com.jesusrojo.pagingdemo.utils.DebugHelp
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import android.view.MenuInflater
import com.jesusrojo.pagingdemo.utils.startMyActivity


class ReposPagingSourceFragment: BaseFragment() {

    private lateinit var pagingDataAdapter: ReposPagingDataAdapter
    private lateinit var viewModel: ReposViewModel
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // INIT PAGING DATA ADAPTER
        pagingDataAdapter = ReposPagingDataAdapter {
            AppHelper.showAlertDetails(requireActivity() as AppCompatActivity, it.toString())
        }

        // INIT RECYCLER VIEW
        //todo sometimes IllegalArgumentException:
        // LayoutManager androidx.recyclerview.widget.LinearLayoutManager@e0fefab is already attached to a RecyclerView:
        binding.recyclerViewItems.apply {
            layoutManager = linearLayoutManager
            adapter = pagingDataAdapter
        }
        binding.recyclerViewItems.adapter =
            pagingDataAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter{ pagingDataAdapter.retry() },
                footer = ReposLoadStateAdapter{ pagingDataAdapter.retry() }
            )

        // ADD LOAD STATE LISTENER TO ADAPTER
        pagingDataAdapter.addLoadStateListener { loadState -> handleMyState(loadState) }

        // INIT & OBSERVE VIEW MODEL
        viewModel = Injection.getReposViewModel(requireActivity() as AppCompatActivity)

        //SEARCH
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY_KEY) ?: DEFAULT_QUERY
        initEditText(query)
        search(query)
    }

    override fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getDatas(query).collectLatest {
                DebugHelp.l("search, query: $query")
                pagingDataAdapter.submitData(lifecycle, it)
            }
        }
    }
}
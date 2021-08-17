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
import com.jesusrojo.pagingdemo.ghrepos.MainGHReposActivity
import com.jesusrojo.pagingdemo.utils.startMyActivity


class ReposPagingSourceFragment : BaseFragment() {

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
        // TODO val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        val query = "kotlin"
        initEditText(query)
        search(query)

        //observeDataFromViewModel()
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

//    private fun observeDataFromViewModel() {
//        lifecycleScope.launch {
//            viewModel.getDatas()
//                .collectLatest {
//                    DebugHelp.l("observe and submitData...")
//                    pagingDataAdapter.submitData(lifecycle, it)
//                }
//        }
//    }

    //MENU
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top_right_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_1 -> {
                requireActivity().startMyActivity<MainGHReposActivity>()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.jesusrojo.pagingdemo.ghrepos.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.ItemsGhreposLayoutBinding
import com.jesusrojo.pagingdemo.ghrepos.MainGHReposActivity
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.util.Resource
import com.jesusrojo.pagingdemo.ghrepos.presentation.ui.adapter.GHReposAdapter
import com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel.GHReposViewModel
import com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel.GHReposViewModelFactory
import com.jesusrojo.pagingdemo.utils.DebugHelp
import com.jesusrojo.pagingdemo.utils.InternetHelp

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GHReposFragment: Fragment() {

    @Inject lateinit var viewModelFactory: GHReposViewModelFactory
    private lateinit var viewModel: GHReposViewModel
    private lateinit var ghReposAdapter: GHReposAdapter

    private lateinit var llm: LinearLayoutManager
    private lateinit var binding: ItemsGhreposLayoutBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ItemsGhreposLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMyAdapter()
        initUi()
        if (savedInstanceState == null) {
            isNetworkNotAvailableShowMessage()
        }
        setupViewModel()
        (activity as MainGHReposActivity).setUpMenuOptionsMainFragment()
    }

    // ADAPTER RELATED
    private fun initMyAdapter() {
       ghReposAdapter = (activity as MainGHReposActivity).ghReposAdapter
        ghReposAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_data", it)
            }
            //ERROR SOMETIMES
           try {
                findNavController().navigate(
                    R.id.action_ghReposFragment_to_ghRepoDetailsFragment, bundle)
            } catch (e: Exception) {
                DebugHelp.l("ERROR $e")
            }
        }
    }

    private fun setNewValuesAdapter(datas: List<GHRepo>) {
        ghReposAdapter.setNewValues(datas as ArrayList<GHRepo>)
        binding.recyclerViewItems.scrollToPosition(0)
    }

    // VIEW MODEL RELATED
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
                    .get(GHReposViewModel::class.java)

        observeViewModel()
        viewModel.fetchGHRepos()
    }

    private fun observeViewModel() {
        viewModel._ghReposMLD.observe(this as LifecycleOwner, { state ->
            when (state) {
                is Resource.Success -> updateUiSuccess(state.data)
                is Resource.Error -> updateUiError(state.message)
                is Resource.Loading -> updateUiLoading()
            }
        })
    }

    private fun onRefresh() {
        if (isNetworkNotAvailableShowMessage()) return
        viewModel.updateGHRepos()
    }

    fun onScrollToLastPositionRecyclerAction() {
        if (isNetworkNotAvailableShowMessage()) return
        viewModel.fetchNextPage()
    }

    fun deleteAllLocal() {
        viewModel.deleteAllLocal()
        ghReposAdapter.deleteAll() // we delete all recycler from here, not from observer
    }

    // UI RELATED
    private fun initUi() {
        llm = LinearLayoutManager(activity?.applicationContext)

        binding.swipeLayoutContainerItems.setOnRefreshListener {
            binding.swipeLayoutContainerItems.isRefreshing = false
            onRefresh()
        }
        binding.recyclerViewItems.apply {
            layoutManager = llm
            adapter = ghReposAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val childCount = ghReposAdapter.itemCount
                        val lastPosition = llm.findLastCompletelyVisibleItemPosition()
                        if (childCount - 1 == lastPosition && isGoneProgressBar()) {
                            onScrollToLastPositionRecyclerAction()
                        }
                    }
                }
            })
        }
    }

    private fun updateUiSuccess(ghRepos: List<GHRepo>?) {
        makeGoneProgressBar()
        if (ghRepos != null) {
            if (ghRepos.isNotEmpty()) {
                setNewValuesAdapter(ghRepos)
                hideTextViewError()
            } else {
                showErrorMessageBadList()
            }
        } else {
            showErrorMessageBadList()
        }
    }

    private fun updateUiError(message: String?) {
        makeGoneProgressBar()
        if (message != null && message.isNotEmpty()) {
            showTextViewError(message)
        } else {
            hideTextViewError()
        }
    }

    private fun updateUiLoading() {
        makeVisibleProgressBar()
        hideTextViewError()
    }

    private fun makeVisibleProgressBar() {
        binding.progressBarItems.visibility = View.VISIBLE
    }

    private fun makeGoneProgressBar() {
        binding.progressBarItems.visibility = View.GONE
    }

    private fun isGoneProgressBar() = binding.progressBarItems.visibility == View.GONE


    private fun hideTextViewError() {
        binding.textviewErrorItems.visibility = View.GONE
        binding.textviewErrorItems.text = ""
    }

    private fun showTextViewError(message: String?) {
         binding.textviewErrorItems.text = message
        binding.textviewErrorItems.visibility = View.VISIBLE
    }

    private fun showErrorMessageBadList() {
         val message = getString(R.string.list_is_empty) + "\n" + getString(R.string.swipe_down_to_get_fresh_data)
        showTextViewError(message)
    }

    // INTERNET
    private fun isNetworkNotAvailableShowMessage(): Boolean {
        ////viewModel.hasInternet=isNetworkNotAvailable() //TODO HERE OR IN VIEW MODEL ?
        if (!InternetHelp.isNetworkAvailable(requireContext())) {
                Toast.makeText(requireContext(), R.string.error_no_internet, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
}
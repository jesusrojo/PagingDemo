package com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.util.Resource
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.*
import com.jesusrojo.pagingdemo.utils.DebugHelp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class GHReposViewModel @Inject constructor(
    private val fetchGHReposUseCase: FetchGHReposUseCase,
    private val fetchNextGHReposUseCase: FetchNextGHReposUseCase,
    private val updateGHReposUseCase: UpdateGHReposUseCase,
    private val deleteAllGHReposUseCase: DeleteAllGHReposUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val vmScope = viewModelScope
    private var jobInitial: Job? = null
    private var jobNextPage: Job? = null
    private var jobUpdate: Job? = null
    private var jobDelete: Job? = null
    val _ghReposMLD: MutableLiveData<Resource<List<GHRepo>>> = MutableLiveData()
    //TODO val ghReposMLD: LiveData<Resource<List<GHRepo>>> = _ghReposMLD

    private var page = 1

    init {
        DebugHelp.l("init viewModel")
//        fetchGHRepos()//todo ok activity, not fragment
    }

    fun fetchGHRepos() {
        DebugHelp.l("fetchGHRepos")
        jobInitial = vmScope.launch(ioDispatcher) {
            updateUiLoading()
            try {
                val apiResults = fetchGHReposUseCase.execute(page)
                updateUiCheckingResults(apiResults)
            } catch (e: Exception) {
                updateUiError(e.message.toString())
            }
        }
    }

    fun fetchNextPage() {
        DebugHelp.l("fetchNextPage")
        jobNextPage = vmScope.launch(ioDispatcher) {
            page++
            updateUiLoading()
            try {
                val apiResults = fetchNextGHReposUseCase.execute(page)
                updateUiCheckingResults(apiResults)
            } catch (e: Exception) {
                updateUiError(e.message.toString())
            }
        }
    }

    fun updateGHRepos() {
        jobUpdate = vmScope.launch(ioDispatcher) {
            updateUiLoading()
            try {
                deleteAllGHReposUseCase.execute()
                page = 1
                val apiResults = updateGHReposUseCase.execute()
                updateUiCheckingResults(apiResults)
            } catch (e: Exception) {
                updateUiError(e.message.toString())
            }
        }
    }

    fun deleteAllLocal() {
        jobDelete = vmScope.launch(ioDispatcher) {
            deleteAllGHReposUseCase.execute()
            updateUiSuccess(emptyList()) // WILL NOT WORK FOR RECYCLER
            //ACTIVITY CHECK FOR EMPTY LIST AND DO NOT CLEAR RECYCLER
        }
    }

    private fun updateUiCheckingResults(apiResults: List<GHRepo>?) {
        if (apiResults != null && apiResults.isNotEmpty()) {
            DebugHelp.l("updateUiCheckingResults {${apiResults.size}}")

            updateUiSuccess(apiResults)
        } else {
            updateUiError("apiResults is null or empty")
        }
    }

    // MLD
    private fun updateUiLoading() {
        _ghReposMLD.postValue(Resource.Loading())
    }

    private fun updateUiError(message: String) {
        _ghReposMLD.postValue(Resource.Error(message))
    }

    private fun updateUiSuccess(apiResults: List<GHRepo>) {
        _ghReposMLD.postValue(Resource.Success(apiResults))
    }

    @CallSuper
    override fun onCleared() {
        println("onCleared")// DebugHelp.l("onCleared") NOT WORKING
        jobInitial?.cancel()
        jobNextPage?.cancel()
        jobUpdate?.cancel()
        jobDelete?.cancel()
        super.onCleared()
    }
}
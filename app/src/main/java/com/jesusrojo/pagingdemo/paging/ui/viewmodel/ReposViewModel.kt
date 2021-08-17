package com.jesusrojo.pagingdemo.paging.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import com.jesusrojo.pagingdemo.paging.data.repository.pagingsource.ReposRepositoryImpl
import kotlinx.coroutines.flow.Flow


class ReposViewModel(
    private val reposRepositoryImpl: ReposRepositoryImpl
): ViewModel() {

    fun  getDatas(): Flow<PagingData<RepoPaging.Repo>> =
            reposRepositoryImpl.fetchDatas()
            .cachedIn(viewModelScope)

    fun  getDatas(query: String): Flow<PagingData<RepoPaging.Repo>> =
        reposRepositoryImpl.fetchDatas(query)
            .cachedIn(viewModelScope)
}
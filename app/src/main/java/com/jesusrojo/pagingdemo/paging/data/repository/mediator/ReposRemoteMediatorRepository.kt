package com.jesusrojo.pagingdemo.paging.data.repository.mediator

import androidx.paging.PagingData
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty
import kotlinx.coroutines.flow.Flow


interface ReposRemoteMediatorRepository {

    fun fetchDatas(query: String): Flow<PagingData<RepoEnty>>
    fun fetchDatas(): Flow<PagingData<RepoEnty>>
}
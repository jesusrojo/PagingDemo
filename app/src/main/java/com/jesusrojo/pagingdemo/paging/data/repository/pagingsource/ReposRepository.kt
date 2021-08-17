package com.jesusrojo.pagingdemo.paging.data.repository.pagingsource

import androidx.paging.PagingData
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import kotlinx.coroutines.flow.Flow


interface ReposRepository {

    fun fetchDatas(query: String): Flow<PagingData<RepoPaging.Repo>>
    fun fetchDatas() : Flow<PagingData<RepoPaging.Repo>>
}
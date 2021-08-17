package com.jesusrojo.pagingdemo.paging.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jesusrojo.pagingdemo.paging.data.local.db.RepoDatabaseService
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty
import kotlinx.coroutines.flow.Flow

class ReposRemoteMediatorRepositoryImpl @ExperimentalPagingApi constructor(
    private val databaseService: RepoDatabaseService,
    private val remoteMediator: ReposRemoteMediator
) : ReposRemoteMediatorRepository {

    @ExperimentalPagingApi
    override fun fetchDatas(query: String): Flow<PagingData<RepoEnty>> {
        remoteMediator.setQuery(query)
        return Pager(
            config = defaultPagingConfig(),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {databaseService.myEntyDao().fetchAllDB()}
        ).flow
    }
    @ExperimentalPagingApi
    override fun fetchDatas(): Flow<PagingData<RepoEnty>> {
        return Pager(
            config = defaultPagingConfig(),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {databaseService.myEntyDao().fetchAllDB()}
        ).flow
    }

    private fun defaultPagingConfig() : PagingConfig {
        return PagingConfig(
            pageSize = 10,
            prefetchDistance = 20,
            enablePlaceholders = false,
            initialLoadSize = 30,
            maxSize = 50
        )
    }
}
package com.jesusrojo.pagingdemo.paging.data.repository.pagingsource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import kotlinx.coroutines.flow.Flow


class ReposRepositoryImpl(
    private val pagingSource: ReposPagingSource
) : ReposRepository {


    override fun fetchDatas(query: String): Flow<PagingData<RepoPaging.Repo>> {
      pagingSource.setQuery(query)
      return Pager(
            defaultPagingConfig(),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override fun fetchDatas(): Flow<PagingData<RepoPaging.Repo>> {
        return Pager(
            config = defaultPagingConfig(),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    private fun defaultPagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 10,
            prefetchDistance = 20,
            enablePlaceholders = false,
            initialLoadSize = 30,
            maxSize = 50
        )
    }
    // PagingConfig also in ReposRemoteMediatorRepositoryImpl
    //
    // WITH CURRENT CONFIG IN PagingConfig: load tree pages
    // IllegalArgumentException:
    // Maximum size must be at least pageSize + 2*prefetchDist,
    // pageSize=10, prefetchDist=30, maxSize=50
    // pageSize=10, prefetchDist=20, maxSize=30
}
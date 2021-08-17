package com.jesusrojo.pagingdemo.paging.data.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import com.jesusrojo.pagingdemo.paging.data.model.ReposResponse
import com.jesusrojo.pagingdemo.paging.data.model.mappers.MapperHelp
import com.jesusrojo.pagingdemo.paging.data.model.mappers.RepoResponseMapper
import com.jesusrojo.pagingdemo.paging.data.remote.GithubApiService
import com.jesusrojo.pagingdemo.paging.data.remote.IN_QUALIFIER
import com.jesusrojo.pagingdemo.utils.DebugHelp
import kotlinx.coroutines.delay
import timber.log.Timber


class ReposPagingSource(
    private val apiService: GithubApiService
) : PagingSource<Int, RepoPaging.Repo>(), RepoResponseMapper<ReposResponse, RepoPaging> {

    private var mCurrentPage: Int = 1
    private val GITHUB_STARTING_PAGE_INDEX = 1
    private var query: String = "Android" // default query

    fun setQuery(query: String) {
       this.query = query
    }

    override fun getRefreshKey(state: PagingState<Int, RepoPaging.Repo>): Int? {
        return  state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoPaging.Repo> {
        val apiQuery ="$query$IN_QUALIFIER"
        val currentPage = params.key ?: GITHUB_STARTING_PAGE_INDEX
        DebugHelp.l("load, currentPage: $currentPage, apiQuery $apiQuery")
        delay(1000) //todo debug
        mCurrentPage = currentPage

        return try {
            apiService.fetchRepos(
                query = apiQuery,
                page = currentPage,
                itemsPerPage = 10) // todo
                .run {
                    val data = mapFromResponse(this)

                    return LoadResult.Page(
                        data = data.repos,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (data.endOfPage) null else currentPage + 1
                    )
                }

        } catch (e: Exception) {
            Timber.d("load, ERROR: $e ##")
            LoadResult.Error(e)
        }
    }
    override fun mapFromResponse(response: ReposResponse): RepoPaging {
        return MapperHelp.mapFromResponse(response, mCurrentPage)
        }
}

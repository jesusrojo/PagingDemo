package com.jesusrojo.pagingdemo.paging.data.repository.mediator

import androidx.paging.*
import androidx.room.withTransaction
import com.jesusrojo.pagingdemo.paging.data.local.db.RepoDatabaseService
import com.jesusrojo.pagingdemo.paging.data.model.mappers.EntityMapper
import com.jesusrojo.pagingdemo.paging.data.model.mappers.RepoResponseMapper
import com.jesusrojo.pagingdemo.paging.data.model.RepoEntyKey
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import com.jesusrojo.pagingdemo.paging.data.model.ReposResponse
import com.jesusrojo.pagingdemo.paging.data.model.mappers.MapperHelp
import com.jesusrojo.pagingdemo.paging.data.remote.GithubApiService
import com.jesusrojo.pagingdemo.paging.data.remote.IN_QUALIFIER
import com.jesusrojo.pagingdemo.utils.DebugHelp
import java.io.InvalidObjectException

@ExperimentalPagingApi
class ReposRemoteMediator(
    private val apiService: GithubApiService,
    private val databaseService: RepoDatabaseService
) : RemoteMediator<Int, RepoEnty>(),
    RepoResponseMapper<ReposResponse, RepoPaging>,
    EntityMapper<RepoPaging.Repo, RepoEnty> {

    private var mCurrentPage: Int = 1
    private var query: String? = null

    fun setQuery(query: String) {
        this.query = query
    }

    //in codelab
//    override suspend fun initialize(): InitializeAction {
//        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
//        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
//        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
//        // triggering remote refresh.
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }

    override suspend fun load(loadType: LoadType,
                              state: PagingState<Int, RepoEnty>): MediatorResult {
        DebugHelp.l("load, loadType: $loadType")
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val keys = getKeyForTheClosestToCurrentItemPosition(state)
                keys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val keys = getKeyForTheFirstItem(state)
                    ?: return MediatorResult.Error(InvalidObjectException("First Item is empty"))

                val previousKey = keys.previousKey
                    ?: return  MediatorResult.Success(endOfPaginationReached = true)

                previousKey
            }

            LoadType.APPEND -> {
                val keys = getKeyForTheLastItem(state)
                    ?: return MediatorResult.Error(InvalidObjectException("Last Item is empty"))

                val nextKey = keys.nextKey
                    ?: return  MediatorResult.Success(endOfPaginationReached = true)

                nextKey
            }
        }

        try {
            // FETCH THE DATA FROM API
            mCurrentPage = page
            val apiQuery ="$query$IN_QUALIFIER"
           ////val itemsPerPage = state.config.pageSize // 10, load 10 bad
            val itemsPerPage = 30 // load 30
            DebugHelp.l("load... page: $page, query $query, itemsPerPage $itemsPerPage")
            val response = apiService.fetchRepos(
                query = apiQuery,
                page = page,
                itemsPerPage = itemsPerPage)

            val data  = mapFromResponse(response)
            databaseService.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    databaseService.myEntyDao().deleteAllDB()
                    databaseService.myEntyKeyDao().clearAllKeys()
                }
                val previousKey = if (page == 1) null else page - 1
                val nextKey = if (data.endOfPage) null else page + 1
                val keys = data.repos.map {
                     DebugHelp.l("map... ${it.toString()} ##")
                    ////RepoEntyKey(keyId = it.id.toLong()  OJO
                    RepoEntyKey(keyId = it.repoId.toLong(), previousKey , nextKey)
                }
                // INSERT TO LOCAL DB
                databaseService.myEntyKeyDao().insertManyKeysDB(keys)
                databaseService.myEntyDao().insertAllDB(mapToEntity(data.repos))
            }
            return  MediatorResult.Success(endOfPaginationReached = data.endOfPage)
        }
        catch (e: Exception) {
            return  MediatorResult.Error(e)
        }

    }

    private suspend fun getKeyForTheFirstItem(state: PagingState<Int, RepoEnty>): RepoEntyKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { enty ->
            databaseService.myEntyKeyDao().getEntyKeyById(enty.repoId?.toInt()!!)
//            databaseService.myEntyKeyDao().getEntyKeyById(enty.id.toInt()) //todo
        }
    }

    private suspend fun getKeyForTheLastItem(state: PagingState<Int, RepoEnty>): RepoEntyKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { enty ->
            databaseService.myEntyKeyDao().getEntyKeyById(enty.repoId?.toInt()!!)
//            databaseService.myEntyKeyDao().getEntyKeyById(enty.id.toInt()) //todo
        }
    }

    private suspend fun getKeyForTheClosestToCurrentItemPosition(state: PagingState<Int, RepoEnty>): RepoEntyKey? {
        return  state.anchorPosition?.let { position->
//            state.closestItemToPosition(position)?.id?.let { it->
//                databaseService.myEntyKeyDao().getEntyKeyById(it.toInt()) //todo
//            }
            state.closestItemToPosition(position)?.repoId?.let { it->
                databaseService.myEntyKeyDao().getEntyKeyById(it.toInt())
            }
        }
    }
    override fun mapFromResponse(response: ReposResponse): RepoPaging {
        return MapperHelp.mapFromResponse(response, mCurrentPage)
    }

    override fun mapToEntity(model: List<RepoPaging.Repo>): List<RepoEnty> {
        return MapperHelp.mapToEntity((model))
    }
}
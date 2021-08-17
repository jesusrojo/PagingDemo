package com.jesusrojo.pagingdemo.ghrepos.data.repository

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepoRaw
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposCacheDataSource
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposLocalDataSource
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposRemoteDataSource
import com.jesusrojo.pagingdemo.ghrepos.data.util.MapperRawToEnty
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository
import com.jesusrojo.pagingdemo.utils.DebugHelp
import javax.inject.Inject


class GHRepoRepositoryImpl @Inject constructor( //todo rename GHRepos
    private val remoteDataSource: GHReposRemoteDataSource,
    private val localDataSource: GHReposLocalDataSource,
    private val cacheDataSource: GHReposCacheDataSource,
    private val mapper: MapperRawToEnty
): GHReposRepository {

    override suspend fun fetchGHRepos(page: Int): List<GHRepo> {
        DebugHelp.l("fetchGHRepos, page  $page")
        return getGHReposFromCache(page)
    }

    private suspend fun getGHReposFromCache(page: Int): List<GHRepo> {
        DebugHelp.l("getGHReposFromCache")
        var ghrepos: List<GHRepo> = ArrayList()
        try {
            ghrepos = cacheDataSource.fetchGHReposFromCache()
        } catch (exception: Exception) {
            DebugHelp.l(exception.message.toString())
        }
        if (ghrepos != null && ghrepos.isNotEmpty()) {//todo test nullpointer
            DebugHelp.l("getGHReposFromCache***********{${ghrepos.size}}")

            return ghrepos
        } else {
            ghrepos = getGHReposFromDB(page)
            cacheDataSource.saveGHReposToCache(ghrepos)
        }

        return ghrepos
    }

    private suspend fun getGHReposFromDB(page: Int): List<GHRepo> {
        DebugHelp.l("getGHReposFromDB")
        var ghrepos: List<GHRepo> = ArrayList()
        try {
            ghrepos = localDataSource.fetchAllInDB()
        } catch (exception: Exception) {
            DebugHelp.l(exception.message.toString())
        }
        if (ghrepos != null && ghrepos.isNotEmpty()) {
            return ghrepos
        } else {
            ghrepos = getGHReposFromAPI(page)
            localDataSource.saveAllToDB(ghrepos)
        }

        return ghrepos
    }

    private suspend fun getGHReposFromAPI(page: Int): List<GHRepo> {
        DebugHelp.l("getGHReposFromAPI, page  $page")
        var ghrepos: List<GHRepo> = ArrayList()
        try {
            val response = remoteDataSource.fetchGHRepos(page)
            val body = response.body()
            if (body != null) {
                val rawGHRepos: List<GHRepoRaw> = body.items
                ghrepos = mapper(rawGHRepos)
            }
        } catch (exception: Exception) {
            DebugHelp.l(exception.message.toString())
        }
        return ghrepos
    }

    override suspend fun fetchNextGHRepos(pageCount: Int): List<GHRepo> {
        DebugHelp.l("fetchNextGHRepos")
        val newListOfGHRepos = getGHReposFromAPI(pageCount)
        localDataSource.deleteAllInDB()
        localDataSource.saveAllToDB(newListOfGHRepos)
        cacheDataSource.saveGHReposToCache(newListOfGHRepos)
        return newListOfGHRepos
    }

    override suspend fun updateGHRepos(): List<GHRepo> {
        DebugHelp.l("updateGHRepos")
        val newListOfGHRepos = getGHReposFromAPI(1)
        localDataSource.deleteAllInDB()
        localDataSource.saveAllToDB(newListOfGHRepos)
        cacheDataSource.saveGHReposToCache(newListOfGHRepos)
        return newListOfGHRepos
    }

    override suspend fun deleteAllGHRepos() {
        DebugHelp.l("deleteAllGHRepos")
        cacheDataSource.deleteGHReposFromCache()
        localDataSource.deleteAllInDB()
    }


    //////////////////////////////////
//    override suspend fun fetchGHRepo(page: Int): Resource<List<GHRepo>> {
//        return responseToResource(remoteDataSource.fetchGHRepo(page))
//    }
//
//    private fun responseToResource(response:Response<GHRepoList>): Resource<List<GHRepo>> {
//        if(response.isSuccessful){
//            response.body()?.let {result->
//
//                val datas = result.items
////                val results = mapperRawToEnty(datas)
//                return Resource.Success(datas)
//            }
//        }
//        return Resource.Error(response.message())
//    }
//////////////////////////////////////
}
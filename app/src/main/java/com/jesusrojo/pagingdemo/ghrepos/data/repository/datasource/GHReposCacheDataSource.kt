package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo


interface GHReposCacheDataSource {
    suspend fun fetchGHReposFromCache():List<GHRepo>
    suspend fun saveGHReposToCache(ghRepos: List<GHRepo>)
    suspend fun deleteGHReposFromCache()
}
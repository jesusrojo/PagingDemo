package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposCacheDataSource
import javax.inject.Inject


class GHReposCacheDataSourceImpl @Inject constructor()
    : GHReposCacheDataSource {

    private var ghrepos = ArrayList<GHRepo>()

    override suspend fun fetchGHReposFromCache(): List<GHRepo> {
        return ghrepos
    }

    override suspend fun saveGHReposToCache(ghRepos: List<GHRepo>) {
        ghrepos.clear()
        ghrepos = ArrayList(ghRepos)
    }

    override suspend fun deleteGHReposFromCache() {
        ghrepos.clear()
    }
}
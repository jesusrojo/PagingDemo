package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl

import com.jesusrojo.pagingdemo.ghrepos.data.api.GHReposAPIService
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHReposList
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposRemoteDataSource
import retrofit2.Response
import javax.inject.Inject


class GHReposRemoteDataSourceImpl @Inject constructor(
    private val service: GHReposAPIService
) : GHReposRemoteDataSource {

    override suspend fun fetchGHRepos(page: Int): Response<GHReposList> {
        return service.fetchGHRepos(page)
    }
}
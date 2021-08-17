package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHReposList
import retrofit2.Response

interface GHReposRemoteDataSource {

    suspend fun fetchGHRepos(page: Int): Response<GHReposList>

}
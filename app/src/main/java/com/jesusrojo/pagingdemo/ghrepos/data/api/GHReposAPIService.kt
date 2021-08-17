package com.jesusrojo.pagingdemo.ghrepos.data.api

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHReposList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GHReposAPIService {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun fetchGHRepos(
        @Query("page") page: Int
    ):  Response<GHReposList>

    // https://api.github.com/search/repositories?q=language:kotlin&sort=stars&page=1

    companion object{
        const val BASE_URL = "https://api.github.com/"
    }
}
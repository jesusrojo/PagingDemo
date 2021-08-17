package com.jesusrojo.pagingdemo.paging.data.remote

import com.jesusrojo.pagingdemo.paging.data.model.ReposResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

interface GithubApiService {

    @GET("search/repositories?sort=stars")
    suspend fun fetchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): ReposResponse

    companion object {
        const val BASE_URL_GITHUB = "https://api.github.com/"
    }

    //https://api.github.com/search/repositories?sort=stars&q=Androidin:name,description&page=0&per_page=10
    //https://api.github.com/search/repositories?sort=stars&q=Android&page=0&per_page=10
}

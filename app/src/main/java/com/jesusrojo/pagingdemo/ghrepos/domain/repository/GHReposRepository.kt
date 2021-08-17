package com.jesusrojo.pagingdemo.ghrepos.domain.repository

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo


interface GHReposRepository {

    suspend fun fetchGHRepos(page: Int): List<GHRepo>?
    suspend fun fetchNextGHRepos(pageCount: Int): List<GHRepo>?
    suspend fun updateGHRepos(): List<GHRepo>?
    suspend fun deleteAllGHRepos()
}
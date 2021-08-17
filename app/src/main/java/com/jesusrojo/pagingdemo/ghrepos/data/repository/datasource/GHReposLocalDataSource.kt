package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo

interface GHReposLocalDataSource {

    suspend fun fetchAllInDB(): List<GHRepo>
    suspend fun saveAllToDB(ghrepos: List<GHRepo>)
    suspend fun deleteAllInDB()
}
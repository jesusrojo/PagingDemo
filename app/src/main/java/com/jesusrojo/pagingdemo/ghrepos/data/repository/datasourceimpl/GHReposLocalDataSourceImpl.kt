package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl

import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDAO
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposLocalDataSource
import javax.inject.Inject


class GHReposLocalDataSourceImpl @Inject constructor (
    private val myDao: GHReposDAO
) : GHReposLocalDataSource {

    override suspend fun fetchAllInDB(): List<GHRepo> {
        return myDao.fetchAll()
    }

    override suspend fun saveAllToDB(ghrepos: List<GHRepo>) {
        myDao.insertAll(ghrepos)
    }

    override suspend fun deleteAllInDB() {
        myDao.deleteAll()
    }
}
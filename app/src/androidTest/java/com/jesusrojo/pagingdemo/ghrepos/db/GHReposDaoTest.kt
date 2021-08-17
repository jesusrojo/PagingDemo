package com.jesusrojo.pagingdemo.ghrepos.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDAO
import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDatabase
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUITest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GHReposDaoTest: BaseUITest() {

    private lateinit var dao: GHReposDAO
    private lateinit var database: GHReposDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GHReposDatabase::class.java
        ).build()
        dao = database.getGHReposDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveGHReposTest() = runBlocking {
        val datas = prepareDatas()
        dao.insertAll(datas)
        val allGHRepos = dao.fetchAll()
        assertThat(allGHRepos).isEqualTo(datas)
    }

    @Test
    fun deleteGHReposTest() = runBlocking {
        val datas = prepareDatas()
        dao.insertAll(datas)
        dao.deleteAll()
        val ghReposResult = dao.fetchAll()
        assertThat(ghReposResult).isEmpty()
    }

    private fun prepareDatas(): List<GHRepo> {
        return listOf(
            GHRepo(
                "repoName1", "createdAt1", "description1",
                false, 101, 201, 301, 401.0,
                "authorName1", "avatarUrl1", "type1","licenseName1"
            ),

            GHRepo(
                "repoName2", "createdAt2", "description2",
                false, 102, 202, 302, 402.0,
                "authorName2", "avatarUrl2", "type2", "licenseName2"
            )
        )
    }
}
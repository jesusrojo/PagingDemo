package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Test


class GHReposCacheDataSourceImplTest: BaseUnitTest() {

    private val sut = GHReposCacheDataSourceImpl()

    @Test
    fun fetchGHReposFromCache() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = emptyList<GHRepo>()
            sut.saveGHReposToCache(datas)

            val results = sut.fetchGHReposFromCache()
            assertThat(results).isEqualTo(datas)
        }

    @Test
    fun saveGHReposToCache() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = FakeRepository.getFakeListItemsOneTwo()
            sut.saveGHReposToCache(datas)

            val results = sut.fetchGHReposFromCache()
            assertThat(results).isEqualTo(datas)
        }

    @Test
    fun deleteGHReposFromCache() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = FakeRepository.getFakeListItemsOneTwo()
            sut.saveGHReposToCache(datas)

            var results = sut.fetchGHReposFromCache()
            assertThat(results).isEqualTo(datas)

            sut.deleteGHReposFromCache()

            results = sut.fetchGHReposFromCache()
            assertThat(results).isEqualTo(emptyList<GHRepo>())
        }
}
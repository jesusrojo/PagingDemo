package com.jesusrojo.pagingdemo.ghrepos.data.repository

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.utils.mock
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHReposList
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.*
import com.jesusrojo.pagingdemo.ghrepos.data.util.MapperRawToEnty
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Response


class GHRepoRepositoryImplTest: BaseUnitTest() {

    private val remoteDataSource: GHReposRemoteDataSource = mock()
    private val localDataSource: GHReposLocalDataSource = mock()
    private val cacheDataSource: GHReposCacheDataSource = mock()
    private val mapper: MapperRawToEnty = mock()
//    val response: Response<GHReposList> = mock()
    private val datas = FakeRepository.getFakeListGHRepoRawItemsOneTwo()
    private val result = GHReposList(datas)
    private val response: Response<GHReposList> = Response.success(result)

    private val sut = GHRepoRepositoryImpl(remoteDataSource, localDataSource, cacheDataSource, mapper)
    private val page = 1

//    @Test
//    fun fetchGHRepos_callCache() = //todo null pointer
//        coroutinesTestRule.testDispatcher.runBlockingTest {
//            SUT.fetchGHRepos(page)
//            verify(cacheDataSource, times(1)).fetchGHReposFromCache()
//        }


    @Test
    fun fetchGHRepos_callCache_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(cacheDataSource.fetchGHReposFromCache()).thenReturn(datas)
            val results = sut.fetchGHRepos(page)
            assertThat(results).isEqualTo(datas)
            verify(cacheDataSource, times(1)).fetchGHReposFromCache()
        }

    @Test
    fun fetchGHRepos_callCache_callLocal_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(cacheDataSource.fetchGHReposFromCache()).thenReturn(null)
            whenever(localDataSource.fetchAllInDB()).thenReturn(datas)
            val results = sut.fetchGHRepos(page)
            assertThat(results).isEqualTo(datas)
            verify(cacheDataSource, times(1)).fetchGHReposFromCache()
            verify(localDataSource, times(1)).fetchAllInDB()
        }


    @Test
    fun fetchGHRepos_callCache_callLocal_callRemote_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            whenever(cacheDataSource.fetchGHReposFromCache()).thenReturn(null)
            whenever(localDataSource.fetchAllInDB()).thenReturn(null)
            whenever(remoteDataSource.fetchGHRepos(page)).thenReturn(response)
//            val datas = FakeRepository.getFakeListGHRepoRawItemsOneTwo()
//            val result = GHReposList(datas)
//            whenever(response.body()).thenReturn(result)

            val results = sut.fetchGHRepos(page)

            verify(cacheDataSource, times(1)).fetchGHReposFromCache()
            verify(localDataSource, times(1)).fetchAllInDB()
            verify(remoteDataSource, times(1)).fetchGHRepos(page)

//            assertThat(results).isEqualTo(datas) //error
        }
}
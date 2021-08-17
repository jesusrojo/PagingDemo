package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl

import com.google.common.truth.Truth.assertThat

import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.utils.mock
import com.jesusrojo.pagingdemo.ghrepos.data.api.GHReposAPIService
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepoRaw
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHReposList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.mockito.Mockito.times
import retrofit2.Response

class GHReposRemoteDataSourceImplTest: BaseUnitTest() {

    private val service: GHReposAPIService = mock()
    private val sut = GHReposRemoteDataSourceImpl(service)
    private val page = 1

    @Test
    fun fetchGHRepos_callService() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val response = sut.fetchGHRepos(page)
            verify(service, times(1)).fetchGHRepos(page)
    }

    @Test
    fun fetchGHRepos_callService_returnNull() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val response = sut.fetchGHRepos(page)
            verify(service, times(1)).fetchGHRepos(page)
            assertThat(response).isEqualTo(null)
    }

    @Test
    fun fetchGHRepos_withListEmptyMocked_isReturnedByService() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val items: List<GHRepoRaw> = emptyList()
            val body: GHReposList? = GHReposList(items)
            val response: Response<GHReposList> = Response.success(body)
            whenever(service.fetchGHRepos(page)).thenReturn(response)

            val responseResult = sut.fetchGHRepos(page)

            val bodyResult = responseResult.body()
            assertThat(bodyResult).isEqualTo(body)

            val itemsResult = bodyResult?.items
            assertThat(itemsResult).isEqualTo(items)

            assertThat(itemsResult?.size).isEqualTo(0)
        }

    @Test
    fun fetchGHRepos_withListFakeMocked_isReturnedByService() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val items: List<GHRepoRaw> = FakeRepository.getFakeListGHRepoRawItemsOneTwo()
            val body: GHReposList? = GHReposList(items)
            val response: Response<GHReposList> = Response.success(body)
            whenever(service.fetchGHRepos(page)).thenReturn(response)

            val responseResult = sut.fetchGHRepos(page)


            val bodyResult = responseResult.body()
            assertThat(bodyResult).isEqualTo(body)

            val itemsResult = bodyResult?.items
            assertThat(itemsResult).isEqualTo(items)

            assertThat(itemsResult?.size).isEqualTo(2)

            val ghRepoRaw = itemsResult?.get(0)
            assertThat(ghRepoRaw?.repoName).isEqualTo("repoName1")
        }
}
package com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.utils.mock
import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDAO
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.times


class GHReposLocalDataSourceImplTest: BaseUnitTest() {

    private val myDAO: GHReposDAO = mock()
    private val sut = GHReposLocalDataSourceImpl(myDAO)

    @Test
    fun fetchAllInDB_getEmptyList_And_callDAO()  =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val results = sut.fetchAllInDB()
            assertThat(results).isEqualTo(emptyList<GHRepo>())
            verify(myDAO, times(1)).fetchAll()
        }

    @Test
    fun fetchAllInDB_callDAO()  =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            sut.fetchAllInDB()
            verify(myDAO, times(1)).fetchAll()
        }

    @Test
    fun fetchAllInDB_returnFakeListMocked()  =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(myDAO.fetchAll()).thenReturn(datas)

            val results = sut.fetchAllInDB()
            assertThat(results).isEqualTo(datas)
        }
}
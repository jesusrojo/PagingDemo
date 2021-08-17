package com.jesusrojo.pagingdemo.ghrepos.usecase

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.utils.mock
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.FetchGHReposUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test


class FetchGHReposUseCaseTest: BaseUnitTest() {

    private val repository: GHReposRepository = mock()
    private val sut = FetchGHReposUseCase(repository)
    private val page = 1

    @Test
    fun execute_callRepository() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
        sut.execute(page)
        verify(repository, times(1)).fetchGHRepos(page)
    }

    @Test
    fun execute_callRepository_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(repository.fetchGHRepos(page)).thenReturn(datas)
            val results = sut.execute(page)
            assertThat(results).isEqualTo(datas)
        }
}
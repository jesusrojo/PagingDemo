package com.jesusrojo.pagingdemo.ghrepos.usecase

import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.utils.mock
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.FetchNextGHReposUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class FetchNextGHReposUseCaseTest: BaseUnitTest() {

    private val repository: GHReposRepository = mock()
    private val sut = FetchNextGHReposUseCase(repository)
    private val page = 1

    @Test
    fun execute_callRepository() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            sut.execute(page)
            verify(repository, times(1)).fetchNextGHRepos(page)
        }
}
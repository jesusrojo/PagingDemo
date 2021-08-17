package com.jesusrojo.pagingdemo.ghrepos.viewmodel

import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
import com.jesusrojo.pagingdemo.ghrepos.data.util.Resource
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.*
import com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel.GHReposViewModel

import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUnitTest
import com.jesusrojo.pagingdemo.ghrepos.utils.getOrAwaitValue
import com.jesusrojo.pagingdemo.ghrepos.utils.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times


class GHReposViewModelTest : BaseUnitTest() {

    private val observer: Observer<Resource<List<GHRepo>>> = mock()
    private lateinit var viewModel: GHReposViewModel

    private fun initViewModelWithFakes() {
        val fakeRepository = FakeRepository()
        val fetchUseCase = FetchGHReposUseCase(fakeRepository)
        val fetchNextUseCase = FetchNextGHReposUseCase(fakeRepository)
        val updateUseCase = UpdateGHReposUseCase(fakeRepository)
        val deleteUseCase = DeleteAllGHReposUseCase(fakeRepository)
        val ioDispatcher = Dispatchers.IO

        viewModel = GHReposViewModel(
            fetchUseCase, fetchNextUseCase,
            updateUseCase, deleteUseCase, ioDispatcher
        )
    }



    //WEIRD!!  PASS ALONE, FAIL WITH OTHERS  TODO
//    @Test
//    fun fetchNextDatas_callTwiceMLD_returnDataOK() =
//        coroutinesTestRule.testDispatcher.runBlockingTest {
//
//            initViewModelWithFakes()
//
//            viewModel.fetchNextPage()
//
//            var state = viewModel._ghReposMLD
//            var results = state.getOrAwaitValue()
//            var datas = results.data
//            assertThat(datas).isEqualTo(null)
//
//            state = viewModel._ghReposMLD
//            results = state.getOrAwaitValue()
//            datas = results.data
//
//            assertThat(datas?.size).isEqualTo(2)
//            assertThat(datas?.get(0).toString())
//                .isEqualTo(FakeRepository.getFakeListItemsOneTwo()[0].toString())
//            assertThat(datas?.get(1).toString())
//                .isEqualTo(FakeRepository.getFakeListItemsOneTwo()[1].toString())
//        }


    // todo fail
    @Test
    fun fetchDatas_callTwiceMLD_returnDataOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            initViewModelWithFakes()

            viewModel.fetchGHRepos()

            var state = viewModel._ghReposMLD
            var results = state.getOrAwaitValue()
            var datas = results.data
            assertThat(datas).isEqualTo(null)

            state = viewModel._ghReposMLD
            results = state.getOrAwaitValue()
            datas = results.data

            assertThat(datas?.size).isEqualTo(2) // todo Fail expected: 2 but was : null
            assertThat(datas?.get(0).toString())
                .isEqualTo(FakeRepository.getFakeListItemsOneTwo()[0].toString())
            assertThat(datas?.get(1).toString())
                .isEqualTo(FakeRepository.getFakeListItemsOneTwo()[1].toString())
        }

    @Test
    fun fetchDatas_callMLD_loadingAndDataNull() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            initViewModelWithFakes()

            viewModel.fetchGHRepos()

            val state = viewModel._ghReposMLD
            val results = state.getOrAwaitValue()
            val datas = results.data
            assertThat(datas).isEqualTo(null)
        }

    @Test
    fun testDeleteAllLocal() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            initViewModelWithFakes()

            viewModel.deleteAllLocal()

            val state = viewModel._ghReposMLD

            val results = state.getOrAwaitValue()
            assertThat(results.data).isEqualTo(emptyList<GHRepo>())
        }


    @Test
    fun fetchDatas_callTwiceMLD() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer: Observer<Resource<List<GHRepo>>> = mock()
            initViewModelWithFakes()
            val state = viewModel._ghReposMLD
            state.observeForever(observer)

            viewModel.fetchGHRepos()

            val captor: ArgumentCaptor<*>? = ArgumentCaptor.forClass(Resource::class.java)
            captor?.run {
                verify(observer, times(2))
            }
        }

//////////////////////////
//    //wierd!! FAIL WHEN i added test before (fetchDatas_callTwiceMLD)
//    // both can not be in the same test?
//    //Missing method call for verify(mock) here:
////-> at com.nhaarman.mockitokotlin2.VerificationKt.verify(Verification.kt:72)
//    @Test
//    fun fetchNextPage_withCaptor() =
//        coroutinesTestRule.testDispatcher.runBlockingTest {
//
//            val observer: Observer<Resource<List<GHRepo>>> = mock()
//            initViewModelWithFakes()
//
//            val state = viewModel._ghReposMLD
//            state.observeForever(observer)
//
//            viewModel.fetchNextPage()
//
////            val captor: ArgumentCaptor<Resource<GHRepo>> =
//            val captor: ArgumentCaptor<*>? =
//                ArgumentCaptor.forClass(Resource::class.java)
//            captor?.run {
//                verify(observer, times(2))
//////                    .onChanged(capture() as Resource<List<GHRepo>>)
////                    .onChanged(capture()!! as Resource<List<GHRepo>>)
////                assertThat(value).isEqualTo(Resource.Loading(null))
////        //      assertThat(results).isEqualTo(Resource.Loading(null))
//            }
//        }
//////////////////////////

}
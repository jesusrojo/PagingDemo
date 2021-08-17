@file:Suppress("PackageDirectoryMismatch")
//package com.jesusrojo.pagingdemo.ghrepos.viewmodel
//
//import androidx.lifecycle.Observer
//import com.google.common.truth.Truth.assertThat
//import com.jesusrojo.pagingdemo.data.model.GHRepo
//import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
//import com.jesusrojo.pagingdemo.data.util.Resource
//import com.jesusrojo.pagingdemo.domain.usecase.DeleteAllGHReposUseCase
//import com.jesusrojo.pagingdemo.domain.usecase.FetchGHReposUseCase
//import com.jesusrojo.pagingdemo.domain.usecase.FetchNextGHReposUseCase
//import com.jesusrojo.pagingdemo.domain.usecase.UpdateGHReposUseCase
//import com.jesusrojo.reposclient.ghrepos.utils.BaseUnitTest
//import com.jesusrojo.reposclient.ghrepos.utils.getOrAwaitValue
//import com.jesusrojo.reposclient.ghrepos.utils.mock
//import com.nhaarman.mockitokotlin2.verify
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Test
//import org.mockito.ArgumentCaptor
//import org.mockito.Mockito.times
//
//
////NOT @RunWith(AndroidJUnit4::class)
////@RunWith(JUnit4::class)
////class GHReposViewModelTest : TestCase()
//class GHReposViewModelTestFakes : BaseUnitTest() {
//
//    private val observer: Observer<Resource<List<GHRepo>>> = mock()
//    private lateinit var viewModel: GHReposViewModel
//
//    private fun initViewModelWithFakes() {
//        val fakeRepository = FakeRepository()
//        val fetchUseCase = FetchGHReposUseCase(fakeRepository)
//        val fetchNextUseCase = FetchNextGHReposUseCase(fakeRepository)
//        val updateUseCase = UpdateGHReposUseCase(fakeRepository)
//        val deleteUseCase = DeleteAllGHReposUseCase(fakeRepository)
//        val ioDispatcher = Dispatchers.IO
//
//        viewModel = GHReposViewModel(fetchUseCase, fetchNextUseCase,
//            updateUseCase, deleteUseCase, ioDispatcher)
//    }
//
//    //FAIL
//    //Missing method call for verify(mock) here:
//    //-> at com.nhaarman.mockitokotlin2.VerificationKt.verify(Verification.kt:72)
////
////    @Test
////    fun updateDatas_returnsUpdatedList() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            initViewModelWithFakes()
////            val state = viewModel._ghReposMLD
////            state.observeForever(observer)
////
////            viewModel.updateGHRepos()
////
////            val results = state.getOrAwaitValue()
////            //todo assertThat(results.data).isEqualTo(FakeRepository.getFakeListItemsThreeFour())
////            assertThat(results.data).isEqualTo(null)
////        }
//
//
//
//    // FAIL
//    //Missing method call for verify(mock) here:
//    //-> at com.nhaarman.mockitokotlin2.VerificationKt.verify(Verification.kt:72)
//    //
////    @Test
////    fun fetchNextPage_withCaptor2TESTE() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            initViewModelWithFakes()
////            val state = viewModel._ghReposMLD
////            state.observeForever(observer)
////
////            viewModel.fetchNextPage()
////            verify(observer, times(2))
////
//////            //https://github.com/muratcanbur/ProjectX/blob/master/app/src/test/java/co/icanteach/projectx/PopularTVShowsViewModelTest.kt
//////            // Then
//////            val slot = slot<Resource<List<GHRepo>>>()
//////            verify { observer.onChanged(capture(slot)) }
//////
//////            assertThat(slot.captured.isLoading()).isTrue()
//////
////////            verify { fetchnPopularTvShowUseCase.fetchMovies(any()) }
//////            // Then
////////            val popularTVShowsFeedViewStateSlots = mutableListOf<GHRepo>()
////////            verify { observer.onChanged(capture(popularTVShowsFeedViewStateSlots)) }
////
////
//////
//////            val results = state.getOrAwaitValue()
//////            val fakeListItemsOneTwo = FakeRepository.getFakeListItemsOneTwo()
//////            assertThat(results.data).isEqualTo(fakeListItemsOneTwo)
////
//////            val captor = ArgumentCaptor.forClass(Resource::class.java)
//////            captor?.run {
////////                verify(observer, times(2)).onChanged(capture()!! as Resource<List<GHRepo>>)
//////                assertThat(value).isEqualTo(Resource.Success(fakeListItemsOneTwo))
//////            }
////
////        }
//
//
//    //    @Test
////    fun fetchNextPage_returnList_RUNNING_BLOKING() {
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            initViewModelWithFakes()
////            val state = viewModel._ghReposMLD
////            state.observeForever(observer)
////
////            viewModel.fetchNextPage()
////
////            val results = state.getOrAwaitValue()
////            assertThat(results.data).isEqualTo(FakeRepository.getFakeListItemsOneTwo())
////        }
////    }
////
////    @Test
////    fun fetchNextPage_returnList_NOT_RUNNING_BLOKING() {
////
////        initViewModelWithFakes()
////        val state = viewModel._ghReposMLD
////        state.observeForever(observer)
////
////        viewModel.fetchNextPage()
////
////        val results = state.getOrAwaitValue()
////        assertThat(results.data).isEqualTo(FakeRepository.getFakeListItemsOneTwo())
////    }
//
//
////    https://github.com/muratcanbur/ProjectX/blob/master/app/src/test/java/co/icanteach/projectx/PopularTVShowsViewModelTest.kt
////    fun `given loading state, when fetchMovies called, then isLoading return true`() {
////
////        // Given
////        val mockedObserver = createPopularTVShowsFeedObserver()
////        popularTVShowsViewModel.status_.observeForever(mockedObserver)
////
////        every { fetchPopularTvShowUseCase.fetchMovies(any()) } returns
////                Observable.just(Resource.Loading)
////
////        // When
////        popularTVShowsViewModel.fetchMovies(1)
////
////        // Then
////        val slot = slot<PopularTVShowsStatusViewState>()
////        verify { mockedObserver.onChanged(capture(slot)) }
////
////        assertThat(slot.captured.isLoading()).isTrue()
////
////        verify { fetchPopularTvShowUseCase.fetchMovies(any()) }
////    }
/////////////////////////////////
//
//    //WE DO NOT USE init ANYMORE
////    @Test
////    fun onInit_uiStateLoadingShow() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            initViewModelWithFakes()
////            val state = viewModel.ghReposMLD
////            state.observeForever(observer)
////
////            val results = state.getOrAwaitValue()
////            val datas = results.data
////            assertThat(datas).isEqualTo(null)
////        }
//
//}
//package com.jesusrojo.pagingdemo.ghrepos.viewmodel
//
//
//import androidx.lifecycle.Observer
//import com.google.common.truth.Truth.assertThat
//import com.jesusrojo.pagingdemo.data.model.GHRepo
//import com.jesusrojo.reposclient.ghrepos.data.repository.FakeRepository
//import com.jesusrojo.pagingdemo.data.util.Resource
//import com.jesusrojo.pagingdemo.domain.usecase.DeleteAllGHReposUseCase
//import com.jesusrojo.pagingdemo.domain.usecase.FetchGHReposUseCase
//import com.jesusrojo.pagingdemo.domain.usecase.FetchNextGHReposUseCase
//import com.jesusrojo.pagingdemo.domain.usecase.UpdateGHReposUseCase
//import com.jesusrojo.reposclient.ghrepos.utils.BaseUnitTest
//import com.jesusrojo.reposclient.ghrepos.utils.getOrAwaitValue
//import com.jesusrojo.reposclient.ghrepos.utils.mock
//import com.nhaarman.mockitokotlin2.verify
//import com.nhaarman.mockitokotlin2.whenever
//import io.mockk.*
//import junit.framework.Assert.assertEquals
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.ArgumentCaptor
//import org.mockito.Captor
//import org.mockito.Mockito.times
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class GHReposViewModelTestMocks : BaseUnitTest() {
//
//    val fetchUseCaseMock: FetchGHReposUseCase = mock()
//    val fetchNextUseCaseMock: FetchNextGHReposUseCase = mock()
//    val updateUseCaseMock: UpdateGHReposUseCase = mock()
//    val deleteUseCaseMock: DeleteAllGHReposUseCase = mock()
//    val ioDispatcher = Dispatchers.IO
//
//    private lateinit var viewModelWithMock: GHReposViewModel
//    private var pageCount = 1
//    private val observer: Observer<Resource<List<GHRepo>>> = mock() //  mock(Observer<User>::class.java)
//    private val preparedDatas = FakeRepository.getFakeListItemsOneTwo()
//
//    @Captor
//    private lateinit var argumentCaptor: ArgumentCaptor<Resource<List<GHRepo>>>
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//
//        viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
//            updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
//    }
//
//
//    //FAIL Missing method call for verify(mock) here:
//    //-> at com.nhaarman.mockitokotlin2.VerificationKt.verify(Verification.kt:72)
//    //
////    @Test
////    fun fetchNextPage_callUseCase( ) =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////            viewModelWithMock.fetchNextPage()
////
////            pageCount++
////            verify(fetchNextUseCaseMock, times(1)).execute(pageCount)
////        }
//
//    // FAIL
//    //
////    Missing method call for verify(mock) here:
////    -> at com.nhaarman.mockitokotlin2.VerificationKt.verify(Verification.kt:72)
//
//    //
////    @Test
////    fun fetchDatas_call_fetchUseCase_return_null_dataInViewModelIsNull() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            whenever(fetchUseCaseMock.execute(pageCount)).thenReturn(null)
////
////            viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            val state = viewModelWithMock._ghReposMLD
////            state.observeForever(observer)
////
////            viewModelWithMock.fetchGHRepos()
////
////            val results = state.getOrAwaitValue()
////            val datas = results.data
////            assertThat(datas).isEqualTo(null)
////        }
//
//
//    // FAIL
//    //
////    Missing method call for verify(mock) here:
////    -> at com.nhaarman.mockitokotlin2.VerificationKt.verify(Verification.kt:72)
//
//    //
////    @Test
////    fun fetchDatas_call_fetchUseCase_return_emptyList_dataInViewModelIsNull() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////            val preparedDatas = emptyList<GHRepo>()
////            whenever(fetchUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
////
////            viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            viewModelWithMock.fetchGHRepos()
////
////            val state = viewModelWithMock._ghReposMLD
////            state.observeForever(observer)
////
////            val results = state.getOrAwaitValue()
////            val datas = results.data
////            // assertThat(datas).isEqualTo(preparedDatas)expected: ERROR [] but was : null
////            assertThat(datas).isEqualTo(null)
////        }
//
//
//    //    // https://medium.com/@iamanbansal/testing-viewmodel-livedata-4a62f34e7c26
////    @Test
////    fun fetchNextPage_withCaptor3()=
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            whenever(fetchNextUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////           // verify(observer,times(3)).onChanged(argumentCaptor.capture())//BAD HERE
////
////            viewModelWithMock.fetchGHRepos()
////
////            viewModelWithMock._ghReposMLD.observeForever(observer)
////            viewModelWithMock.fetchNextPage()
////
////            verify(observer,times(3)).onChanged(argumentCaptor.capture()) //OK HERE
////
////            val values = argumentCaptor.allValues
////            assertThat(values[0].data).isEqualTo(null)
////            //assertThat(values[0]).isEqualTo(Resource.Error("", null))BAD DIFFERENT OBJECT
////            assertThat(values[1].data).isEqualTo(null)
////            assertThat(values[2].data).isEqualTo(null)
////        }
//
//
//
//
////    @Test
////    fun onInit_call_fetchUseCase_return_emptyList_dataInViewModelIsNull() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////            val preparedDatas = emptyList<GHRepo>()
////            whenever(fetchUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
////
////            viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            val state = viewModelWithMock.ghReposMLD
////            state.observeForever(observer)
////
////            val results = state.getOrAwaitValue()
////
////            val datas = results.data
////            // assertThat(datas).isEqualTo(preparedDatas)expected: ERROR [] but was : null
////            assertThat(datas).isEqualTo(null)
////        }
//
//    // ERROR but was:  null
////    @Test
////    fun onInit_call_fetchUseCase_return_oneItemList() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            // WE NEED  runBlockingTest OR ERROR
////             whenever(fetchUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
//////            runBlockingTest {
//////                whenever(fetchUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
//////            }
////
////            //WE NEED TO INIT HERE
////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            val state = viewModelWithMock.ghReposMLD
////            state.observeForever(observer)
////
////            val results = state.getOrAwaitValue()
////            val datas = results.data
////            assertThat(datas).isEqualTo(preparedDatas) // ERROR but was:  null
////        }
//
//
////    @Test
////    fun fetchNextPage_withCaptor2TESTE() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            whenever(fetchNextUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
////
////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            val state = viewModelWithMock._ghReposMLD
////            state.observeForever(observer)
////
////            viewModelWithMock.fetchNextPage()
////            verify(observer, times(2))
////
/////////////////////////////////
//////            val results = state.getOrAwaitValue()
//////            assertThat(results.data).isEqualTo(preparedDatas)
//////
//////            val captor = ArgumentCaptor.forClass(Resource::class.java)
//////            captor?.run {
//////                verify(observer, times(2)).onChanged(capture()!! as Resource<List<GHRepo>>)
//////                assertThat(value).isEqualTo(Resource.Success(preparedDatas))
//////            }
////////////////////////////
////        }
//
//
//
//
//////https://stackoverflow.com/questions/63339306/viewmodel-unit-testing-multiple-view-states-with-livedata-coroutines-and-mockk
////    @Test
////    fun fetchNextPage_testAllStates() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            // GIVEN
////            val observer = mockk<Observer<Resource<List<GHRepo>>>>()
////            val slot = slot<Resource<List<GHRepo>>>()
////            val list = arrayListOf<Resource<List<GHRepo>>>()
////            every { observer.onChanged(capture(slot)) } answers {
////                list.add(slot.captured) //store captured value
////            }
////
////            // WHEN
//////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
//////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////            viewModelWithMock._ghReposMLD.observeForever(observer)
////            viewModelWithMock.fetchNextPage()
////
////            // THEN
//////            assertThat(list.size).isEqualTo(2)
//////            assertThat(list[0]).isEqualTo(Resource.Loading(null))
//////            assertThat(list[0].data).isEqualTo(null)
//////            assertThat(list[1]).isEqualTo(Resource.Success(preparedDatas))
//////            assertThat(list[1].data).isEqualTo(preparedDatas)
////            val results = viewModelWithMock._ghReposMLD.getOrAwaitValue()
////            assertThat(results.data).isEqualTo(preparedDatas)
////            assertThat(viewModelWithMock._ghReposMLD.value?.data).isEqualTo(preparedDatas) // ERROR but was:  null
////
////        //            assertEquals(viewModelWithMock.ghReposMLD.value?.data, preparedDatas )
//////            assertEquals(viewModelWithMock.ghReposMLD.value, Resource.Success(preparedDatas))
////        }
//
//
//    //error Missing calls inside every { ... } block.
////    @Test
////    fun fetchNextPage_testAllStates1() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            val observer = mockk<Observer<Resource<List<GHRepo>>>>()
////
////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            //https://stackoverflow.com/questions/63775513/getting-error-mockkexception-no-answer-found-for-observer8-onchanged-androi
////            //Arrange
////            every { observer.onChanged(any()) } answers {}
////            every {viewModelWithMock.ghReposMLD.value} returns Resource.Success(preparedDatas)
////
////            //Act
////            viewModelWithMock.fetchNextPage()
////
////            //Assert
//////            verify {
//////                observer.onChanged(Resource.Loading(null))
//////                observer.onChanged(Resource.Success(preparedDatas))
//////            }
////
////            verifySequence {
////                observer.onChanged(Resource.Loading(null))
////                observer.onChanged(Resource.Loading(null))
////                observer.onChanged(Resource.Success(preparedDatas))
////            }
////        }
//
//
////    @Test
////    fun fetchNextPage_testAllStates2() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            // GIVEN
////            val observer = mockk<Observer<Resource<List<GHRepo>>>> {
////                every { onChanged(any()) } just Runs
////            }
////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////            viewModelWithMock._ghReposMLD.observeForever(observer)
////
////            // WHEN
//////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
//////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////            viewModelWithMock.fetchNextPage()
////
////            // THEN
////            verifySequence {
////                observer.onChanged(Resource.Loading(null))
////                observer.onChanged(Resource.Loading(null))
////                observer.onChanged(Resource.Success(preparedDatas))
////            }
////            assertEquals(viewModelWithMock._ghReposMLD.value, preparedDatas )
////
////        }
//
//
//
//    //https://github.com/android/architecture-components-samples/blob/master/LiveDataSample/app/src/test/java/com/android/example/livedatabuilder/LiveDataViewModelTest.kt
////    @Test
////    fun getCurrentWeather_loading() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////
////            // Start with a paused dispatcher in the FakeDataSource
////            coroutinesTestRule.testDispatcher.pauseDispatcher()
////
////            // Keep observing currentWeather
////            viewModelWithMock.ghReposMLD.observeForTesting {
////
////                // Verify that the first value is Loading
////                assertEquals(viewModelWithMock.ghReposMLD.value, Resource.Loading(null))
////
////                // Resume fake dispatcher so it emits a new value
////                coroutinesTestRule.testDispatcher.resumeDispatcher()
////
////                // Verify the new value is available
////                assertEquals(viewModelWithMock.ghReposMLD.value, preparedDatas )
////            }
////        }
//
///////////////////////////////////////////
////    @Test
////    fun fetchNextPage_withCaptor2TESTE() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            whenever(fetchNextUseCaseMock.execute(pageCount)).thenReturn(preparedDatas)
////
////            val viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            val state = viewModelWithMock.ghReposMLD
////            state.observeForever(observer)
////
////            viewModelWithMock.fetchNextPage()
////            verify(observer, times(2))
////
///////////////////////////////////
////////            val results = state.getOrAwaitValue()
////////            assertThat(results.data).isEqualTo(preparedDatas)
////////
////////            val captor = ArgumentCaptor.forClass(Resource::class.java)
////////            captor?.run {
//////////                verify(observer, times(2)).onChanged(capture()!! as Resource<List<GHRepo>>)
////////                assertThat(value).isEqualTo(Resource.Success(preparedDatas))
////////            }
//////////////////////////////
//////            //https://github.com/muratcanbur/ProjectX/blob/master/app/src/test/java/co/icanteach/projectx/PopularTVShowsViewModelTest.kt
//////            val captor = ArgumentCaptor.forClass(Resource::class.java)
//////            captor?.run {
//////                val slot = slot<Resource<List<GHRepo>>>()
//////                verify { observer.onChanged(capture(slot)) }
//////            }
//////
//////
//////            assertThat(slot.captured.isLoading()).isTrue()
//////
////////            verify { fetchnPopularTvShowUseCase.fetchMovies(any()) }
//////            // Then
////////            val popularTVShowsFeedViewStateSlots = mutableListOf<GHRepo>()
////////            verify {
///////////////////////////////////////
////            //https://stackoverflow.com/questions/63339306/viewmodel-unit-testing-multiple-view-states-with-livedata-coroutines-and-mockk
////
////            //create mockk object
////            val observer = mockk<Observer<Resource<List<GHRepo>>>>()
////
////            //create slot
////            val slot = slot<Resource<List<GHRepo>>>()
////
////            //create list to store values
////            val list = arrayListOf<Resource<List<GHRepo>>>()
////
////            //start observing
////            viewModelWithMock.ghReposMLD.observeForever(observer)
////
////
////            //capture value on every call
////            every { observer.onChanged(capture(slot)) } answers {
////                //store captured value
////                list.add(slot.captured)
////            }
////
////            viewModelWithMock.ghReposMLD
////
////            //assert your values here
////            ////////////////////////////////////////
////            // GIVEN
////            val observer1 = mockk<Observer<Resource<List<GHRepo>>>> {
////                every { onChanged(any()) } just Runs }
////            viewModelWithMock.ghReposMLD.observeForever(observer1)
////            // WHEN
////            viewModelWithMock.fetchNextPage()
////
////            // THEN
////            verifySequence {
////                observer1.onChanged(yourExpectedValue1)
////                observer1.onChanged(yourExpectedValue2)
////            }
////        }
//
//
//////////////////////////////////////////////////////////////////////////////////////////
//
////    @Test //WE NOT USE init   anymore
////    fun onInit_fetchUseCase_callUseCase() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
///////////////////////////// TODO
////////            //ERROR if I init viewModel here, OK IF  init in before
//////            viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
//////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
//////            verify(fetchUseCaseMock, times(1)).execute(pageCount)
////////            org.mockito.exceptions.verification.TooLittleActualInvocations:  fetchGHReposUseCase.execute(1);
/////////////////////////////
////            verify(fetchUseCaseMock, times(1)).execute(pageCount)
////            //SOMETIME PASS INDIVIDUAL BUT NOT WILL ALL   //  error But was 0 times
////        }
////
////    @Test
////    fun onInit_call_fetchUseCase_return_null_dataInViewModelIsNull() =
////        coroutinesTestRule.testDispatcher.runBlockingTest {
////
////            whenever(fetchUseCaseMock.execute(pageCount)).thenReturn(null)
////
////            viewModelWithMock = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock,
////                updateUseCaseMock, deleteUseCaseMock, ioDispatcher)
////
////            val state = viewModelWithMock.ghReposMLD
////            state.observeForever(observer)
////
////            val results = state.getOrAwaitValue()
////
////            val datas = results.data
////            assertThat(datas).isEqualTo(null)
////        }
/////////////////////////////
//
//
//}
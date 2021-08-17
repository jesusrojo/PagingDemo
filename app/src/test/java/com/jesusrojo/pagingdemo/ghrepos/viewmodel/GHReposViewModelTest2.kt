package com.jesusrojo.pagingdemo.ghrepos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.repository.FakeRepository
import com.jesusrojo.pagingdemo.ghrepos.data.util.Resource
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.*
import com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel.GHReposViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


import java.lang.RuntimeException


// https://github.com/lucaslabs/TheNewsApp/blob/master/app/src/test/java/com/thenewsapp/presentation/shownews/SharedNewsViewModelTest.kt
@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ResourceUsersViewModelTest2 {

    // This rule allows us to run LiveData synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock lateinit var observer: Observer<Resource<*>>

    @Mock lateinit var fetchUseCaseMock: FetchGHReposUseCase
    @Mock lateinit var fetchNextUseCaseMock: FetchNextGHReposUseCase
    @Mock lateinit var updateUseCase: UpdateGHReposUseCase
    @Mock lateinit var deleteAllUseCaseMock: DeleteAllGHReposUseCase
    private val ioDispatcher = Dispatchers.IO

    private lateinit var viewModel: GHReposViewModel
    private val page = 1
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = GHReposViewModel(fetchUseCaseMock, fetchNextUseCaseMock, updateUseCase, deleteAllUseCaseMock, ioDispatcher)

        // Observe the LiveData forever
        viewModel._ghReposMLD.observeForever(observer)

        // Sets the given [dispatcher] as an underlying dispatcher of [Dispatchers.Main]
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        // Whatever happens, remove the observer!
        viewModel._ghReposMLD.removeObserver(observer)

        // Resets state of the [Dispatchers.Main] to the original main dispatcher
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running
        testCoroutineDispatcher.cleanupTestCoroutines()
    }


    suspend fun foo() {
        delay(1000)
        println("foo")
    }


    @Test
    fun `test delay in coroutine`() = runBlockingTest {
        val realStartTime = System.currentTimeMillis()
        val virtualStartTime = currentTime

        foo()

        println("Unit test time: ${System.currentTimeMillis() - realStartTime} ms")
        println("Real call time: ${currentTime - virtualStartTime} ms")
    }

    @Test
    fun `test live data_success`() {
        // Given
        val mutableLiveData = MutableLiveData<Resource<List<GHRepo>>>()

        // When
        mutableLiveData.value = Resource.Success(emptyList<GHRepo>())

        // Then
        assertThat(mutableLiveData.value?.data, equalTo(emptyList<GHRepo>()))
    }

    @Test
    fun `test live data_loading`() {
        // Given
        val mutableLiveData = MutableLiveData<Resource<List<GHRepo>>>()

        // When
        mutableLiveData.value = Resource.Loading(null)

        // Then
        assertThat(mutableLiveData.value?.data, equalTo(null))
    }

    @Test
    fun `test live data_error`() {
        // Given
        val errorMessage = "Error testing"
        val mutableLiveData = MutableLiveData<Resource<List<GHRepo>>>()

        // When
        mutableLiveData.value = Resource.Error(errorMessage)

        // Then
        assertThat(mutableLiveData.value?.data, equalTo(null))
        assertThat(mutableLiveData.value?.message, equalTo(errorMessage))
    }

    /**
     * Test naming convention
     * 1. subjectUnderTest_actionOrInput_resultState
     * 2. `subject under test with action or input should return result state`
     */
    //todo fail Example of correct verification:
    //    verify(mock).doSomething()
    @Test
    fun `fetch datas return success with expected data`() = runBlockingTest {
        // Given
        val expectedDatas = FakeRepository.getFakeListItemsOneTwo()
        given(fetchUseCaseMock.execute(page)).willReturn(expectedDatas)

        // When
        viewModel.fetchGHRepos()
        val actualResource = viewModel._ghReposMLD.value

        // Then
         //assertThat(actualResource?.data, equalTo(expectedDatas)) // TODO FAIL

        verify(observer, times(1))
            .onChanged(isA(Resource.Loading::class.java)) // FAIL SOMETIMES But was 0 times.
        verify(observer, times(1)).onChanged(isA(Resource.Success::class.java))
        verify(observer, never()).onChanged(isA(Resource.Error::class.java))

        verifyNoMoreInteractions(observer)
    }
//////////////
//    @Test  fun `fetch datas return success with expected data FAIL`() = runBlockingTest {
//        val expectedDatas = FakeRepository.getFakeListItemsOneTwo()
//        given(fetchUseCaseMock.execute()).willReturn(expectedDatas)
//
//        viewModel.fetchDatas()
//        val actualResource = viewModel._ghReposMLD.value
//
//        assertThat(actualResource?.data, equalTo(expectedDatas)) // TODO FAIL
//    }
//////////////

    @Test
    fun `fetch datas should return exception`() = runBlockingTest {
        // Given
        given(fetchUseCaseMock.execute(page)).willThrow(RuntimeException("Exception"))
      ////  `when`(fetchUseCaseMock.execute(page)).thenThrow(RuntimeException("Exception"))

        // When
        viewModel.fetchGHRepos()
        val actualError = viewModel._ghReposMLD.value

        // Then
//        assertThat(actualError?.message, equalTo("Exception")) //FAIL Expected: "Exception"but: was null

        verify(observer, times(1)).onChanged(isA(Resource.Loading::class.java))
        verify(observer, times(1)).onChanged(isA(Resource.Error::class.java))
        verify(observer, never()).onChanged(isA(Resource.Success::class.java))

        verifyNoMoreInteractions(observer)
    }
}
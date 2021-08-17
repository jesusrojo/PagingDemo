package com.jesusrojo.pagingdemo.ghrepos.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule

open class BaseUnitTest {

    //https://medium.com/swlh/kotlin-coroutines-in-android-unit-test-28ff280fc0d5
    protected val testDispatcher = TestCoroutineDispatcher()

    //https://medium.com/androiddevelopers/easy-coroutines-in-android-viewmodelscope-25bffb605471
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    // SAME AS BEFORE ?
//    @get:Rule
//    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()



}
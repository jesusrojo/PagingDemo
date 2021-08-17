package com.jesusrojo.pagingdemo.ghrepos.ui


import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.ghrepos.presentation.hilt.idlingResource
import com.jesusrojo.pagingdemo.ghrepos.utils.BaseUITest
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu
import org.junit.After
import org.junit.Before

import org.junit.Test


class BrowseActivityTest : BaseUITest(){

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
    @Test
    fun openMenuClickVersionGHRepos() {
//        sleep(1000)
        closeSoftKeyboard();
        openContextualActionModeOverflowMenu();
//        openMenu()// not working
//        openActionBarOverflowOrOptionsMenu(context); //

        clickMenu(R.id.menu_item_1)
        assertDisplayed("Fragment_GHRepos")
        assertDisplayed(R.id.swipe_layout_container_items)
        assertDisplayed(R.id.recycler_view_items)
    }

//    @Test
//    fun hidesLoader() {
//        assertNotDisplayed(R.id.progress_bar_items)
//    }
//
////    @Test
////    fun displaysLoaderWhileFetchingDatas() {
////        IdlingRegistry.getInstance().unregister(idlingResource)
////        ////Thread.sleep(500)
////        assertDisplayed(R.id.progress_bar_items)
////    }
}
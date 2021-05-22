package com.daffa.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyMovie = DummyData.getMovies()
    private val dummyTvShow = DummyData.getTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun a_loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun b_loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fb_favorite)).perform(click())

        onView(withId(R.id.scroll)).perform(swipeUp())
        onView(withId(R.id.tv_detail_imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_score)).check(matches(isDisplayed()))
    }

    @Test
    fun c_loadTvShows() {
        onView(withId(R.id.menu_tv_show)).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun d_loadDetailTvShow() {
        onView(withId(R.id.menu_tv_show)).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fb_favorite)).perform(click())

        onView(withId(R.id.scroll)).perform(swipeUp())
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_score)).check(matches(isDisplayed()))
    }

    @Test
    fun e_loadFavMovies() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun f_loadDetailFavMovies() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )

        onView(withId(R.id.scroll)).perform(swipeUp())
        onView(withId(R.id.tv_detail_imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_score)).check(matches(isDisplayed()))
    }

    @Test
    fun g_loadFavTvShows() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())

        onView(withId(R.id.rv_favorite_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun h_loadDetailFavTvShows() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())

        onView(withId(R.id.rv_favorite_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )

        onView(withId(R.id.scroll)).perform(swipeUp())
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_score)).check(matches(isDisplayed()))
    }
}
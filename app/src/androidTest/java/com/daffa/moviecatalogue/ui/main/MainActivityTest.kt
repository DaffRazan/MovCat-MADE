package com.daffa.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyDetailMovie = DummyData.dummyDetailMovieResponse()
    private val dummyDetailTvShows = DummyData.dummyDetailTvShowResponse()

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
    fun loadMovies() {
        val dataMovie: List<Movie> = arrayListOf()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size - 1))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
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
    fun loadTvShows() {
        val dataTvShow: List<TvShow> = arrayListOf()
        onView(withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTvShow.size - 1))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
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
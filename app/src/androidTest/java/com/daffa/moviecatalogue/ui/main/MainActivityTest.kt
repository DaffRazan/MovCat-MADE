package com.daffa.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.utils.DummyData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyMovie = DummyData.generateDummyMovies()
    private val dummyTvShows = DummyData.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size - 1))
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
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_detail_imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc))
            .check(matches(withText(dummyMovie[0].description)))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyMovie[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovie[0].score)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size - 1))
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
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tv_detail_imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc))
            .check(matches(withText(dummyTvShows[0].description)))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText(dummyTvShows[0].releaseDate)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyTvShows[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyTvShows[0].score)))
    }
}
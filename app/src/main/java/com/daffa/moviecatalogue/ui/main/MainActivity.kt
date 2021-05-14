package com.daffa.moviecatalogue.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.databinding.ActivityMainBinding
import com.daffa.moviecatalogue.ui.favorite.fragments.FavoriteFragment
import com.daffa.moviecatalogue.ui.main.movies.MoviesFragment
import com.daffa.moviecatalogue.ui.main.tvshows.TvShowsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val moviesFragment = MoviesFragment()
        val tvShowFragment = TvShowsFragment()
        val favoriteFragment = FavoriteFragment()

        makeCurrentFragment(moviesFragment)

        activityMainBinding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_movie -> {
                    activityMainBinding.mainAppText.setText(getString(R.string.fragment_movie_title))
                    makeCurrentFragment(moviesFragment)
                }
                R.id.menu_tv_show -> {
                    activityMainBinding.mainAppText.setText(getString(R.string.fragment_tvShow_title))
                    makeCurrentFragment(tvShowFragment)
                }
                R.id.menu_favorite -> {
                    activityMainBinding.mainAppText.setText(getString(R.string.fragment_favorite_title))
                    makeCurrentFragment(favoriteFragment)
                }
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}
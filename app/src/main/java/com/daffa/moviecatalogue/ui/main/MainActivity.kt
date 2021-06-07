package com.daffa.moviecatalogue.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.databinding.ActivityMainBinding
import com.daffa.moviecatalogue.ui.main.movies.MoviesFragment
import com.daffa.moviecatalogue.ui.main.tvshows.TvShowsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        if (savedInstanceState == null) {
            makeCurrentFragment(MoviesFragment())
        }

        activityMainBinding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_movie -> {
                    activityMainBinding.mainAppText.setText(getString(R.string.fragment_movie_title))
                    makeCurrentFragment(MoviesFragment())
                }
                R.id.menu_tv_show -> {
                    activityMainBinding.mainAppText.setText(getString(R.string.fragment_tvShow_title))
                    makeCurrentFragment(TvShowsFragment())
                }
                R.id.menu_favorite -> {
                    activityMainBinding.mainAppText.setText(getString(R.string.fragment_favorite_title))
                    goToFavoriteModule()
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    private fun goToFavoriteModule() {
        val fragment = instantiateFavoriteFragment(pathModule)
        if (fragment != null) {
            makeCurrentFragment(fragment)
        }
    }

    private fun instantiateFavoriteFragment(className: String): Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private val pathModule: String
        get() = "com.daffa.moviecatalogue.favorite.fragments.FavoriteFragment"

}
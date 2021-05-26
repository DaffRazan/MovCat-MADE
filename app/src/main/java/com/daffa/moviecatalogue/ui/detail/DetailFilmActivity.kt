package com.daffa.moviecatalogue.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.daffa.moviecatalogue.utils.Constants
import com.daffa.moviecatalogue.utils.Constants.API_BACKDROP_PATH
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private val viewModel: DetailFilmViewModel by viewModel()

    private lateinit var detailFilmBinding: ActivityDetailFilmBinding
    private lateinit var detailMovieResponse: DetailMovieResponse
    private var dataCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(detailFilmBinding.root)

        supportActionBar?.hide()

        showLoading(true)

        val extras = intent.extras
        if (extras != null) {
            val dataFilm = extras.getString(EXTRA_FILM)
            dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataFilm != null && dataCategory != null) {
                viewModel.setFilm(dataFilm, dataCategory.toString())

                setupFavorite()

                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie.observe(this, {
                        when (it) {
                            is Resource.Loading -> showLoading(
                                true
                            )
                            is Resource.Success -> {
                                if (it.data != null) {
                                    showLoading(false)
                                    it.data?.let { detailMovie -> handleDataDetailMovie(detailMovie) }
                                }
                            }
                            is Resource.Error -> {
                                showLoading(false)
                                Toast.makeText(
                                    applicationContext,
                                    "Something goes wrong...",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                } else {
                    viewModel.getDetailTvShow.observe(this, {
                        when (it) {
                            is Resource.Loading -> showLoading(
                                true
                            )
                            is Resource.Success -> {
                                if (it.data != null) {
                                    showLoading(false)
                                    it.data?.let { detailTvShow -> handleDetailTvShow(detailTvShow) }
                                }
                            }
                            is Resource.Error -> {
                                showLoading(false)
                                Toast.makeText(
                                    applicationContext,
                                    "Something goes wrong...",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
            }

        }

    }

    private fun showLoading(state: Boolean) {
        detailFilmBinding.progressBar.isVisible = state
        detailFilmBinding.fbFavorite.isGone = state
        detailFilmBinding.icDate.isGone = state
        detailFilmBinding.rbScore.isGone = state
    }

    private fun handleDataDetailMovie(movie: Movie) {
        with(movie) {
            val runtimeText = resources.getString(R.string.runtime_text, this.runtime.toString())

            Glide.with(this@DetailFilmActivity)
                .load(API_BACKDROP_PATH + this.backdrop_path)
                .into(detailFilmBinding.tvDetailImgBackdrop)

            Glide.with(this@DetailFilmActivity)
                .load(API_POSTER_PATH + this.poster_path)
                .into(detailFilmBinding.tvDetailImgPoster)

            detailFilmBinding.collapsingToolbar.title = this.title
            detailFilmBinding.tvDetailDesc.text = this.overview
            detailFilmBinding.tvDetailScore.text = this.vote_average.toString()
            detailFilmBinding.tvDetailReleaseDate.text = this.release_date
            detailFilmBinding.tvDetailGenre.text = this.genres
            detailFilmBinding.tvDetailRuntime.text = runtimeText
        }
    }

    private fun handleDetailTvShow(tvShow: TvShow) {
        with(tvShow) {
            com.bumptech.glide.Glide.with(this@DetailFilmActivity)
                .load(Constants.API_BACKDROP_PATH + this.backdrop_path)
                .into(detailFilmBinding.tvDetailImgBackdrop)

            com.bumptech.glide.Glide.with(this@DetailFilmActivity)
                .load(Constants.API_POSTER_PATH + this.poster_path)
                .into(detailFilmBinding.tvDetailImgPoster)

            detailFilmBinding.collapsingToolbar.title = this.title
            detailFilmBinding.tvDetailDesc.text = this.overview
            detailFilmBinding.tvDetailScore.text = this.vote_average.toString()
            detailFilmBinding.tvDetailReleaseDate.text = this.release_date
            detailFilmBinding.tvDetailGenre.text = this.genres
            detailFilmBinding.tvDetailRuntime.text = this.runtime
        }
    }

    private fun setupFavorite() {
        if (dataCategory == MOVIE) {
            viewModel.getDetailMovie.observe(this, { movie ->
                when (movie) {
                    is Resource.Loading -> showLoading(
                        true
                    )
                    is Resource.Success -> {
                        if (movie.data != null) {
                            showLoading(false)
                            var statusFavoriteMovie = movie.data!!.isFavorite
                            setFavoriteFilm(statusFavoriteMovie)
                            detailFilmBinding.fbFavorite.setOnClickListener {
                                statusFavoriteMovie = !statusFavoriteMovie
                                viewModel.setFavoriteMovie(movie.data!!, statusFavoriteMovie)
                                setFavoriteFilm(statusFavoriteMovie)
                            }
                        }
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            applicationContext,
                            "Something goes wrong...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        } else if (dataCategory == TV_SHOW) {
            viewModel.getDetailTvShow.observe(this, { tvShow ->
                when (tvShow) {
                    is Resource.Loading -> showLoading(
                        true
                    )
                    is Resource.Success -> {
                        if (tvShow.data != null) {
                            showLoading(false)
                            var statusFavoriteTvShow = tvShow.data!!.isFavorite
                            setFavoriteFilm(statusFavoriteTvShow)
                            detailFilmBinding.fbFavorite.setOnClickListener {
                                statusFavoriteTvShow = !statusFavoriteTvShow
                                viewModel.setFavoriteTvShow(tvShow.data!!, statusFavoriteTvShow)
                                setFavoriteFilm(statusFavoriteTvShow)
                            }
                        }
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            applicationContext,
                            "Something goes wrong...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }

    private fun setFavoriteFilm(state: Boolean) {
        if (state) {
            detailFilmBinding.fbFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_filled
                )
            )
        } else {
            detailFilmBinding.fbFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_unfilled
                )
            )
        }
    }

}
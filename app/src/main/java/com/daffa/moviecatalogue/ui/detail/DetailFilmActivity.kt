package com.daffa.moviecatalogue.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.daffa.moviecatalogue.utils.Constants.API_BACKDROP_PATH
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.vo.Status

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var viewModel: DetailFilmViewModel
    private lateinit var detailFilmBinding: ActivityDetailFilmBinding
    private lateinit var detailMovieResponse: DetailMovieResponse
    private var dataCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(detailFilmBinding.root)

        supportActionBar?.hide()

        showLoading(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val dataFilm = extras.getString(EXTRA_FILM)
            dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataFilm != null && dataCategory != null) {
                viewModel.setFilm(dataFilm, dataCategory.toString())

                setupFavorite()

                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie.observe(this, {
                        when (it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (it.data != null) {
                                    showLoading(false)
                                    handleDataDetailMovie(it.data)
                                }
                            }
                            Status.ERROR -> {
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
                        when (it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (it.data != null) {
                                    showLoading(false)
                                    handleDetailTvShow(it.data)
                                }
                            }
                            Status.ERROR -> {
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

            detailFilmBinding.tvDetailTitle.text = this.title
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
                .load(com.daffa.moviecatalogue.utils.Constants.API_BACKDROP_PATH + this.backdrop_path)
                .into(detailFilmBinding.tvDetailImgBackdrop)

            com.bumptech.glide.Glide.with(this@DetailFilmActivity)
                .load(com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH + this.poster_path)
                .into(detailFilmBinding.tvDetailImgPoster)

            detailFilmBinding.tvDetailTitle.text = this.title
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
                when (movie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showLoading(false)
                            var statusFavoriteMovie = movie.data.isFavorite
                            setFavoriteFilm(statusFavoriteMovie)
                            detailFilmBinding.fbFavorite.setOnClickListener {
                                statusFavoriteMovie = !statusFavoriteMovie
                                viewModel.setFavoriteMovie(movie.data, statusFavoriteMovie)
                                setFavoriteFilm(statusFavoriteMovie)
                            }
                        }
                    }
                    Status.ERROR -> {
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
                when (tvShow.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showLoading(false)
                            var statusFavoriteTvShow = tvShow.data.isFavorite
                            setFavoriteFilm(statusFavoriteTvShow)
                            detailFilmBinding.fbFavorite.setOnClickListener {
                                statusFavoriteTvShow = !statusFavoriteTvShow
                                viewModel.setFavoriteTvShow(tvShow.data, statusFavoriteTvShow)
                                setFavoriteFilm(statusFavoriteTvShow)
                            }
                        }
                    }
                    Status.ERROR -> {
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
            detailFilmBinding.fbFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_filled))
        } else {
            detailFilmBinding.fbFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_unfilled))
        }
    }

}
package com.daffa.moviecatalogue.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.daffa.moviecatalogue.utils.Constants.API_BACKDROP_PATH
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.vo.Status

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

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

        detailFilmBinding.fbFavorite.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_FILM)
            dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataId != null && dataCategory != null) {
                viewModel.setFilm(dataId, dataCategory.toString())

                setupState()

                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie.observe(this, {
                        when(it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (it.data != null) {
                                    showLoading(false)
                                    handleDataDetailMovie(it.data)
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                toast("Something goes wrong")
                            }
                        }
                    })
                } else {
                    viewModel.getDetailTvShow.observe(this, {
                        when(it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (it.data != null) {
                                    showLoading(false)
                                    handleDetailTvShow(it.data)
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(applicationContext, "Something goes wrong...", Toast.LENGTH_SHORT).show()
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

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleDataDetailMovie(movie: MovieEntity) {
        with (movie) {
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

    private fun handleDetailTvShow(tvShow: TvShowEntity) {
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

    private fun setupState() {
        if (dataCategory == MOVIE) {
             viewModel.getDetailMovie.observe(this, { movie ->
                 when(movie.status) {
                     Status.LOADING -> showLoading(true)
                     Status.SUCCESS -> {
                         if (movie.data != null) {
                             showLoading(false)
                             val state = movie.data.isFav
                             setFavoriteFilm(state)
                         }
                     }
                     Status.ERROR -> {
                         showLoading(false)
                         Toast.makeText(applicationContext, "Something goes wrong...", Toast.LENGTH_SHORT).show()
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
                            val state = tvShow.data.isFav
                            setFavoriteFilm(state)
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(applicationContext, "Something goes wrong...", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fb_favorite -> {
                onFavButtonClicked()
            }
        }
    }

    private fun onFavButtonClicked() {
        if (dataCategory == MOVIE) {
            viewModel.setFavoriteMovie()
        } else if (dataCategory == TV_SHOW) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFavoriteFilm(state: Boolean) {
        if (state) {
            detailFilmBinding.fbFavorite.setImageResource(R.drawable.ic_favorite_filled)
            Log.d("set fav", "setFavoriteFilm: ")
        } else {
            detailFilmBinding.fbFavorite.setImageResource(R.drawable.ic_favorite_unfilled)
            Log.d("unfav", "setFavoriteFilm: ")
        }
    }


}
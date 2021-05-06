package com.daffa.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.daffa.moviecatalogue.utils.Constants.API_BACKDROP_PATH
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE

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

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_FILM)
            dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataId != null && dataCategory != null) {
                viewModel.setFilm(dataId, dataCategory.toString())

                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie().observe(this, {
                        handleDataDetailMovie(it)
                    })
                } else {
                    viewModel.getDetailTvShow().observe(this, {
                        handleDetailTvShow(it)
                    })
                }
            }

        }

    }

    private fun handleDataDetailMovie(resourceMovie: Resource<DetailMovieResponse>) {
        when (resourceMovie) {
            is Resource.Loading -> detailFilmBinding.progressBar.visibility = View.VISIBLE
            is Resource.Empty -> detailFilmBinding.progressBar.visibility = View.GONE
            is Resource.Success -> {
                detailFilmBinding.progressBar.visibility = View.GONE
                resourceMovie.data.let { populateDetailMovie(it) }
            }
            is Resource.Error -> {
                detailFilmBinding.progressBar.visibility = View.GONE
                Toast.makeText(this, resourceMovie.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleDetailTvShow(resourceTvShow: Resource<DetailTvShowResponse>) {
        when (resourceTvShow) {
            is Resource.Loading -> detailFilmBinding.progressBar.visibility = View.VISIBLE
            is Resource.Empty -> detailFilmBinding.progressBar.visibility = View.GONE
            is Resource.Success -> {
                detailFilmBinding.progressBar.visibility = View.GONE
                resourceTvShow.data.let { populateDetailTvShow(it) }
            }
            is Resource.Error -> {
                detailFilmBinding.progressBar.visibility = View.GONE
                Toast.makeText(this, resourceTvShow.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateDetailMovie(detailMovieResponse: DetailMovieResponse) {
        with(detailMovieResponse) {
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

            val genreBuilder = StringBuilder()
            val iterator = this.genres.iterator()
            while (iterator.hasNext()) {
                val obj = iterator.next()
                if (iterator.hasNext()) {
                    genreBuilder.append(obj.name)
                    genreBuilder.append(", ")
                } else {
                    genreBuilder.append(obj.name)
                }
            }
            detailFilmBinding.tvDetailGenre.text = genreBuilder

            detailFilmBinding.tvDetailRuntime.text = runtimeText
        }
    }

    private fun populateDetailTvShow(detailTvShowResponse: DetailTvShowResponse) {
        with(detailTvShowResponse) {
            val epsSeasonText = resources.getString(
                R.string.episodeSeason_text,
                this.number_of_episodes.toString(),
                this.number_of_seasons.toString()
            )

            Glide.with(this@DetailFilmActivity)
                .load(API_BACKDROP_PATH + this.backdrop_path)
                .into(detailFilmBinding.tvDetailImgBackdrop)

            Glide.with(this@DetailFilmActivity)
                .load(API_POSTER_PATH + this.poster_path)
                .into(detailFilmBinding.tvDetailImgPoster)

            detailFilmBinding.tvDetailTitle.text = this.name
            detailFilmBinding.tvDetailDesc.text = this.overview
            detailFilmBinding.tvDetailScore.text = this.vote_average.toString()
            detailFilmBinding.tvDetailReleaseDate.text = this.first_air_date

            val genreBuilder = StringBuilder()
            val iterator = this.genres.iterator()
            while (iterator.hasNext()) {
                val obj = iterator.next()
                if (iterator.hasNext()) {
                    genreBuilder.append(obj.name)
                    genreBuilder.append(", ")
                } else {
                    genreBuilder.append(obj.name)
                }
            }
            detailFilmBinding.tvDetailGenre.text = genreBuilder

            detailFilmBinding.tvDetailRuntime.text = epsSeasonText
        }
    }
}
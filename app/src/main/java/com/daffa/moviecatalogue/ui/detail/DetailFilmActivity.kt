package com.daffa.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.daffa.moviecatalogue.utils.Constants.API_BACKDROP_PATH
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import retrofit2.http.GET
import javax.inject.Inject

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
                        populateDetailMovie(it)
                    })
                } else {
                    viewModel.getDetailTvShow().observe(this, {
                        populateDetailTvShow(it)
                    })
                }
            }

        }

    }

    private fun populateDetailMovie(detailMovieResponse: DetailMovieResponse) {
        with(detailMovieResponse) {
            Glide.with(this@DetailFilmActivity)
                .load(API_BACKDROP_PATH + this.backdrop_path)
                .into(detailFilmBinding.tvDetailImgBackdrop)

            Glide.with(this@DetailFilmActivity)
                .load(API_POSTER_PATH + this.poster_path)
                .into(detailFilmBinding.tvDetailImgPoster)

            detailFilmBinding.tvDetailTitle.text = this.title
            detailFilmBinding.tvDetailDesc.text = this.overview
            detailFilmBinding.tvScore.text = this.vote_average.toString()
            detailFilmBinding.tvReleaseDate.text = this.release_date
            detailFilmBinding.tvGenre.text = this.genres[0].name
        }
    }

    private fun populateDetailTvShow(detailTvShowResponse: DetailTvShowResponse) {
        with(detailTvShowResponse) {
            Glide.with(this@DetailFilmActivity)
                .load(API_BACKDROP_PATH + this.backdrop_path)
                .into(detailFilmBinding.tvDetailImgBackdrop)

            Glide.with(this@DetailFilmActivity)
                .load(API_POSTER_PATH + this.poster_path)
                .into(detailFilmBinding.tvDetailImgPoster)

            detailFilmBinding.tvDetailTitle.text = this.name
            detailFilmBinding.tvDetailDesc.text = this.overview
            detailFilmBinding.tvScore.text = this.vote_average.toString()
            detailFilmBinding.tvReleaseDate.text = this.first_air_date
            detailFilmBinding.tvGenre.text = this.genres[0].name
        }
    }
}
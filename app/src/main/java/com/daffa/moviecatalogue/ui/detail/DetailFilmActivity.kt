package com.daffa.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailFilmBinding: ActivityDetailFilmBinding
    private lateinit var dataFilm: FilmEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(detailFilmBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailFilmViewModel::class.java]

        val filmEntity: FilmEntity? = intent.getParcelableExtra(EXTRA_DATA)
        val type: String? = intent.getStringExtra(EXTRA_TYPE)

        if (filmEntity != null){
            dataFilm = viewModel.getFilmById(filmEntity.id, type)

        }

        detailFilmBinding.tvDetailTitle.text = dataFilm.title
        detailFilmBinding.tvDetailImgPoster.setImageResource(dataFilm.imgPoster)
        detailFilmBinding.tvReleaseDate.text = dataFilm.releaseDate
        detailFilmBinding.tvGenre.text = dataFilm.genre
        detailFilmBinding.tvScore.text = dataFilm.score
        detailFilmBinding.tvDetailDesc.text = dataFilm.description
    }

}
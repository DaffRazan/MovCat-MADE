package com.daffa.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.DATA_DESTINATION
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.DATA_ID
import retrofit2.http.GET
import javax.inject.Inject

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val DATA_EXTRA = "DATA_EXTRA"
        const val DATA_EXTRA_ID = "DATA_EXTRA_ID"
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel = ViewModelProvider(this, viewModelFactory)[DetailFilmViewModel::class.java]

    private lateinit var detailFilmBinding: ActivityDetailFilmBinding
    private lateinit var dataFilm: FilmEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(detailFilmBinding.root)

       intent.getIntegerArrayListExtra(DATA_EXTRA)?.apply {
           viewModel.setDataExtra(get(DATA_DESTINATION), get(DATA_ID))
       }

//        val filmEntity: FilmEntity? = intent.getParcelableExtra(EXTRA_DATA)
//        val type: String? = intent.getStringExtra(EXTRA_TYPE)

//        if (filmEntity != null) {
//            dataFilm = viewModel.getFilmById(filmEntity.id, type)
//
//        }

//        detailFilmBinding.detailAppname.text = StringBuilder("Detail ").append(type)
//        detailFilmBinding.tvDetailTitle.text = dataFilm.title
//        detailFilmBinding.tvDetailImgPoster.setImageResource(dataFilm.imgPoster)
//        detailFilmBinding.tvReleaseDate.text = dataFilm.releaseDate
//        detailFilmBinding.tvGenre.text = dataFilm.genre
//        detailFilmBinding.tvScore.text = dataFilm.score
//        detailFilmBinding.tvDetailDesc.text = dataFilm.description
    }
}
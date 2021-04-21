package com.daffa.moviecatalogue.ui.detail


import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.utils.Constants
import com.daffa.moviecatalogue.utils.DummyData

class DetailFilmViewModel : ViewModel() {
    private fun getMovies(): List<FilmEntity> = ArrayList(DummyData.generateDummyMovies())
    private fun getTvShows(): List<FilmEntity> = ArrayList(DummyData.generateDummyTvShows())

    fun getFilmById(Id: String?, Type: String?): FilmEntity {
        lateinit var result: FilmEntity

        if (Type.equals(Constants.TYPE_MOVIE, ignoreCase = true)) {
            val listMovie = getMovies()

            for (movie in listMovie) {
                if (movie.id == Id) {
                    result = movie
                    break
                }
            }
        } else {
            val listTvShow = getTvShows()
            for (tvShow in listTvShow) {
                if (tvShow.id == Id) {
                    result = tvShow
                    break
                }
            }
        }
        return result
    }

}
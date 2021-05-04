package com.daffa.moviecatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.data.source.remote.network.ApiService
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.MainViewModel

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        fragmentMoviesBinding.rvMovie.layoutManager = LinearLayoutManager(activity)
        adapter = MoviesAdapter()
        fragmentMoviesBinding.rvMovie.setHasFixedSize(true)


        viewModel.getMovies.observe(viewLifecycleOwner, Observer {
            adapter.setMovies(it)
        })
        fragmentMoviesBinding.rvMovie.adapter = adapter

        adapter.setOnItemClickCallback(object :
            MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(id: String) {
                selectedMovie(id)
            }
        })
    }

    //loading
    private fun showLoading(state: Boolean) {
        fragmentMoviesBinding.progressBar.isVisible = state
        fragmentMoviesBinding.rvMovie.isGone = state
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, MOVIE)

        requireActivity().startActivity(intent)
    }
}
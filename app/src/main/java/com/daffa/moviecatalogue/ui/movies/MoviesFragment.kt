package com.daffa.moviecatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.data.source.remote.network.ApiService
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.utils.Status
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
            handleData(it)
        })

        adapter.setOnItemClickCallback(object :
            MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(id: String) {
                selectedMovie(id)
            }
        })
    }

    private fun handleData(resource: Resource<MovieResponse>) {
        when (resource) {
            is Resource.Loading -> fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            is Resource.Empty -> fragmentMoviesBinding.progressBar.visibility = View.GONE
            is Resource.Success -> {
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                resource.data.let { data -> adapter.setMovies(data.results) }
                Log.d("suksesData", resource.toString())
                fragmentMoviesBinding.rvMovie.adapter = adapter
            }
            is Resource.Error -> {
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, MOVIE)

        requireActivity().startActivity(intent)
    }
}
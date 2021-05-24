package com.daffa.moviecatalogue.ui.main.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.ui.MoviesAdapter
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.core.ui.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.MainViewModel

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter

    private var _binding: FragmentMoviesBinding? = null
    private val fragmentMoviesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMoviesBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

            adapter = MoviesAdapter()

            fragmentMoviesBinding.rvMovie.layoutManager = LinearLayoutManager(context)
            fragmentMoviesBinding.rvMovie.setHasFixedSize(true)
            fragmentMoviesBinding.rvMovie.adapter = adapter

            viewModel.getMovies.observe(viewLifecycleOwner, handleData)

        }

    }

    private val handleData = Observer<Resource<List<Movie>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let { data -> adapter.setMovies(data) }
                    adapter.setOnItemClickCallback(object :
                        MoviesAdapter.OnItemClickCallback {
                        override fun onItemClicked(id: String) {
                            selectedMovie(id)
                        }
                    })
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    showLoading(false)
                    activity?.toast("Something goes wrong")
                }
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, MOVIE)

        requireActivity().startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMoviesBinding.progressBar.visibility = View.GONE
        }
    }
}
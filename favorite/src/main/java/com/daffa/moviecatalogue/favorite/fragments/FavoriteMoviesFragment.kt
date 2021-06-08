package com.daffa.moviecatalogue.favorite.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.core.ui.MoviesAdapter
import com.daffa.moviecatalogue.favorite.FavoriteViewModel
import com.daffa.moviecatalogue.favorite.databinding.FragmentFavoriteMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val favMoviesBinding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        val view = favMoviesBinding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        with(favMoviesBinding.rvFavoriteMovie) {
            if (this.adapter != null) {
                this.adapter = null
            }
        }

        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            adapter = MoviesAdapter()

            favMoviesBinding.rvFavoriteMovie.layoutManager = LinearLayoutManager(context)
            favMoviesBinding.rvFavoriteMovie.setHasFixedSize(true)
            favMoviesBinding.rvFavoriteMovie.adapter = adapter

            viewModel.getFavoriteMovies.observe(viewLifecycleOwner, { favMovies ->
                if (favMovies.isNotEmpty()) {
                    adapter.setMovies(favMovies)
                    favMoviesBinding.tvFilmNotFound.visibility = View.GONE
                    favMoviesBinding.lavFilmNotFound.visibility = View.GONE
                } else {
                    favMoviesBinding.tvFilmNotFound.visibility = View.VISIBLE
                    favMoviesBinding.lavFilmNotFound.visibility = View.VISIBLE
                    favMoviesBinding.rvFavoriteMovie.visibility = View.GONE
                }
            })

            adapter.setOnItemClickCallback(object :
                MoviesAdapter.OnItemClickCallback {
                override fun onItemClicked(id: String) {
                    selectedMovie(id)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies.observe(viewLifecycleOwner, { favMovies ->
            if (favMovies.isNotEmpty()) {
                adapter.setMovies(favMovies)
                favMoviesBinding.tvFilmNotFound.visibility = View.GONE
                favMoviesBinding.lavFilmNotFound.visibility = View.GONE
            } else {
                favMoviesBinding.tvFilmNotFound.visibility = View.VISIBLE
                favMoviesBinding.lavFilmNotFound.visibility = View.VISIBLE
                favMoviesBinding.rvFavoriteMovie.visibility = View.GONE
            }
        })
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, DetailFilmViewModel.MOVIE)

        requireActivity().startActivity(intent)
    }
}
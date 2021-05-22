package com.daffa.moviecatalogue.ui.favorite.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.databinding.FragmentFavoriteMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.ui.main.movies.MoviesAdapter
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar

class FavoriteMoviesFragment : Fragment() {

    private lateinit var favMoviesBinding: FragmentFavoriteMoviesBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favMoviesBinding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return favMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(favMoviesBinding.rvFavoriteMovie)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            adapter = MoviesAdapter()
            adapter.setOnItemClickCallback(object :
                MoviesAdapter.OnItemClickCallback {
                override fun onItemClicked(id: String) {
                    selectedMovie(id)
                }
            })

            viewModel.getFavMovies.observe(viewLifecycleOwner, { favMovies ->
                if (favMovies != null) {
                    adapter.submitList(favMovies)
                }
            })

            favMoviesBinding.rvFavoriteMovie.layoutManager = LinearLayoutManager(context)
            favMoviesBinding.rvFavoriteMovie.setHasFixedSize(true)
            favMoviesBinding.rvFavoriteMovie.adapter = adapter

        }
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, DetailFilmViewModel.MOVIE)

        requireActivity().startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { favMovies ->
            if (favMovies != null) {
                adapter.submitList(favMovies)
            }
        })
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavoriteMovie(it) }

                val snackBar = Snackbar.make(requireView(), getString(R.string.undo), Snackbar.LENGTH_LONG)
                snackBar.setAction(getString(R.string.confirm_undo)) { _ ->
                    movieEntity?.let { viewModel.setFavoriteMovie(it) }
                }
                snackBar.show()
            }
        }
    })
}
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
import com.daffa.moviecatalogue.databinding.FragmentFavoriteTvShowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.ui.main.tvshows.TvShowsAdapter
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar


class FavoriteTvShowsFragment : Fragment() {

    private lateinit var favTvShowsBinding: FragmentFavoriteTvShowsBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favTvShowsBinding = FragmentFavoriteTvShowsBinding.inflate(layoutInflater, container, false)
        return favTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(favTvShowsBinding.rvFavoriteTvShow)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            adapter = TvShowsAdapter()
            adapter.setOnItemClickCallback(object :
                TvShowsAdapter.OnItemClickCallback {
                override fun onItemClicked(id: String) {
                    selectedTvShows(id)
                }
            })

            viewModel.getFavTvShows.observe(viewLifecycleOwner, { favTvShows ->
                if (favTvShows != null) {
                    adapter.submitList(favTvShows)
                }
            })

            favTvShowsBinding.rvFavoriteTvShow.layoutManager = LinearLayoutManager(context)
            favTvShowsBinding.rvFavoriteTvShow.setHasFixedSize(true)
            favTvShowsBinding.rvFavoriteTvShow.adapter = adapter

        }
    }

    private fun selectedTvShows(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, DetailFilmViewModel.TV_SHOW)

        requireActivity().startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { favTvShows ->
            if (favTvShows != null) {
                adapter.submitList(favTvShows)
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
                val tvShowEntity = adapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }

                val snackBar = Snackbar.make(requireView(), getString(R.string.undo), Snackbar.LENGTH_LONG)
                snackBar.setAction(getString(R.string.confirm_undo)) { _ ->
                    tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }
                }
                snackBar.show()
            }
        }
    })
}
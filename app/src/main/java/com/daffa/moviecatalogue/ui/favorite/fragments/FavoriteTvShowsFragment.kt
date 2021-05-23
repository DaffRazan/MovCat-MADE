package com.daffa.moviecatalogue.ui.favorite.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.databinding.FragmentFavoriteTvShowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.ui.main.tvshows.TvShowsAdapter
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.FavoriteViewModel

class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowsBinding? = null
    private val favTvShowsBinding get() = _binding!!

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTvShowsBinding.inflate(layoutInflater, container, false)
        val view = favTvShowsBinding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            viewModel.getFavoriteTvShows.observe(viewLifecycleOwner, { favTvShows ->
                if (favTvShows != null) {
                    adapter.setTvShow(favTvShows)
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
        viewModel.getFavoriteTvShows.observe(viewLifecycleOwner, { favTvShows ->
            if (favTvShows != null) {
                adapter.setTvShow(favTvShows)
            }
        })
    }


}
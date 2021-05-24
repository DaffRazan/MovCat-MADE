package com.daffa.moviecatalogue.favorite.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.core.ui.TvShowsAdapter
import com.daffa.moviecatalogue.favorite.FavoriteViewModel
import com.daffa.moviecatalogue.favorite.databinding.FragmentFavoriteTvShowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowsBinding? = null
    private val favTvShowsBinding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModel()
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
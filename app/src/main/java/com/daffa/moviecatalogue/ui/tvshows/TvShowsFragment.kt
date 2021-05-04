package com.daffa.moviecatalogue.ui.tvshows

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
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.ui.movies.MoviesAdapter
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import javax.inject.Inject


class TvShowsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: TvShowsAdapter

    private lateinit var fragmentTvshowsBinding: FragmentTvshowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTvshowsBinding = FragmentTvshowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvshowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        fragmentTvshowsBinding.rvTvShow.layoutManager = LinearLayoutManager(activity)
        adapter = TvShowsAdapter()
        fragmentTvshowsBinding.rvTvShow.setHasFixedSize(true)

        viewModel.getTvShows.observe(viewLifecycleOwner, Observer {
            adapter.setTvShows(it)
        })
        fragmentTvshowsBinding.rvTvShow.adapter = adapter

        adapter.setOnItemClickCallback(object :
            TvShowsAdapter.OnItemClickCallback {
            override fun onItemClicked(id: String) {
                selectTvShow(id)
            }
        })

    }

    private fun selectTvShow(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY,TV_SHOW)

        requireActivity().startActivity(intent)
    }
}
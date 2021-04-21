package com.daffa.moviecatalogue.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.databinding.ItemsTvshowsBinding
import com.daffa.moviecatalogue.ui.movies.MoviesViewModel


class TvShowsFragment : Fragment() {
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

        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowsViewModel::class.java]
            val tvShows = viewModel.getTvShows()

            val tvShowsAdapter = TvShowsAdapter()
            tvShowsAdapter.setTvShows(tvShows)

            with(fragmentTvshowsBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

}
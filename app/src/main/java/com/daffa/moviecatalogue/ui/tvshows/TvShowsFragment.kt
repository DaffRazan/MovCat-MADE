package com.daffa.moviecatalogue.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.ui.movies.MoviesAdapter
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import javax.inject.Inject


class TvShowsFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tvShowsAdapter = TvShowsAdapter()

        with(fragmentTvshowsBinding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }

        viewModel.getTvShows.observe(viewLifecycleOwner, { handleStat(it) })

    }

    private fun handleStat(resource: Resource<TvShowResponse>) {
        when (resource) {
            is Resource.Loading -> fragmentTvshowsBinding.isLoading = true
            is Resource.Empty -> fragmentTvshowsBinding.isLoading = false
            is Resource.Success -> {
                fragmentTvshowsBinding.isLoading = false
                resource.data.let { data -> adapter.data = data.results.toMutableList() }
            }
            is Resource.Error -> {
                fragmentTvshowsBinding.isLoading = false
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
package com.daffa.moviecatalogue.ui.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.viewmodels.MainViewModel


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

        viewModel.getTvShows.observe(viewLifecycleOwner, {
            handleData(it)
        })

        adapter.setOnItemClickCallback(object :
            TvShowsAdapter.OnItemClickCallback {
            override fun onItemClicked(id: String) {
                selectTvShow(id)
            }
        })

    }

    private fun handleData(resource: Resource<TvShowResponse>) {
        when (resource) {
            is Resource.Loading -> fragmentTvshowsBinding.progressBar.visibility = View.VISIBLE
            is Resource.Empty -> fragmentTvshowsBinding.progressBar.visibility = View.GONE
            is Resource.Success -> {
                fragmentTvshowsBinding.progressBar.visibility = View.GONE
                resource.data.let { data -> adapter.setTvShows(data.results) }
                fragmentTvshowsBinding.rvTvShow.adapter = adapter
            }
            is Resource.Error -> {
                fragmentTvshowsBinding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectTvShow(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY,TV_SHOW)

        requireActivity().startActivity(intent)
    }
}
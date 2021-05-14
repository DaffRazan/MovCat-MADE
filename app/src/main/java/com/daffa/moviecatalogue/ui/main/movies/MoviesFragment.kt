package com.daffa.moviecatalogue.ui.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.utils.SortUtils.RELEASE_DATE_ASC
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import com.daffa.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

            adapter = MoviesAdapter()

            viewModel.getMovies(RELEASE_DATE_ASC).observe(viewLifecycleOwner, handleData)

            fragmentMoviesBinding.rvMovie.layoutManager = LinearLayoutManager(context)
            fragmentMoviesBinding.rvMovie.setHasFixedSize(true)
            fragmentMoviesBinding.rvMovie.adapter = adapter

        }

    }

    private val handleData = Observer<Resource<PagedList<MovieEntity>>> {
        if (it != null) {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    adapter.submitList(it.data)
                    adapter.setOnItemClickCallback(object :
                        MoviesAdapter.OnItemClickCallback {
                        override fun onItemClicked(id: String) {
                            selectedMovie(id)
                        }
                    })
                    adapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, "Something goes wrong...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, MOVIE)

        requireActivity().startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        fragmentMoviesBinding.progressBar.isVisible = state
        fragmentMoviesBinding.rvMovie.isGone = state
    }
}
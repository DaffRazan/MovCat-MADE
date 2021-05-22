package com.daffa.moviecatalogue.ui.main.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.utils.SortUtils
import com.daffa.moviecatalogue.utils.SortUtils.NEWEST_RELEASE
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
        setHasOptionsMenu(true)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

            adapter = MoviesAdapter()

            fragmentMoviesBinding.rvMovie.layoutManager = LinearLayoutManager(context)
            fragmentMoviesBinding.rvMovie.setHasFixedSize(true)
            fragmentMoviesBinding.rvMovie.adapter = adapter

            viewModel.getMovies(NEWEST_RELEASE).observe(viewLifecycleOwner, handleData)

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
                    activity?.toast("Something goes wrong")
                }
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun selectedMovie(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, MOVIE)

        requireActivity().startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMoviesBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""

        when (item.itemId) {
            R.id.action_newest_release -> sort = SortUtils.NEWEST_RELEASE
            R.id.action_oldest_release -> sort = SortUtils.OLDEST_RELEASE
            R.id.action_best_vote -> sort = SortUtils.BEST_VOTE
            R.id.action_worst_vote -> sort = SortUtils.WORST_VOTE
        }

        viewModel.getMovies(sort).observe(viewLifecycleOwner, handleData)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }
}
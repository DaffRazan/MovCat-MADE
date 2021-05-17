package com.daffa.moviecatalogue.ui.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.utils.SortUtils
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import com.daffa.moviecatalogue.vo.Status


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
        setHasOptionsMenu(true)
        return fragmentTvshowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

            adapter = TvShowsAdapter()

            viewModel.getTvShows(SortUtils.NEWEST_RELEASE).observe(viewLifecycleOwner, handleData)

            fragmentTvshowsBinding.rvTvShow.layoutManager = LinearLayoutManager(context)
            fragmentTvshowsBinding.rvTvShow.setHasFixedSize(true)
            fragmentTvshowsBinding.rvTvShow.adapter = adapter
        }

    }

    private val handleData = Observer<Resource<PagedList<TvShowEntity>>> {
        if (it != null) {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    adapter.submitList(it.data)
                    adapter.setOnItemClickCallback(object :
                        TvShowsAdapter.OnItemClickCallback {
                        override fun onItemClicked(id: String) {
                            selectTvShow(id)
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

    private fun selectTvShow(id: String) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.EXTRA_FILM, id)
        intent.putExtra(DetailFilmActivity.EXTRA_CATEGORY, TV_SHOW)

        requireActivity().startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        fragmentTvshowsBinding.progressBar.isVisible = state
        fragmentTvshowsBinding.rvTvShow.isGone = state
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

        viewModel.getTvShows(sort).observe(viewLifecycleOwner, handleData)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }
}
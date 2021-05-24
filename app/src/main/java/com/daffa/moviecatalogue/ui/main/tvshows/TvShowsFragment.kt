package com.daffa.moviecatalogue.ui.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.ui.TvShowsAdapter
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: TvShowsAdapter

    private var _binding: FragmentTvshowsBinding? = null
    private val fragmentTvShowsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvshowsBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvShowsBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)

            adapter = TvShowsAdapter()

            viewModel.getTvShows.observe(viewLifecycleOwner, handleData)

            fragmentTvShowsBinding.rvTvShow.layoutManager = LinearLayoutManager(context)
            fragmentTvShowsBinding.rvTvShow.setHasFixedSize(true)
            fragmentTvShowsBinding.rvTvShow.adapter = adapter
        }

    }

    private val handleData = Observer<Resource<List<TvShow>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let { data -> adapter.setTvShow(data) }
                    adapter.setOnItemClickCallback(object :
                        TvShowsAdapter.OnItemClickCallback {
                        override fun onItemClicked(id: String) {
                            selectTvShow(id)
                        }
                    })
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
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
        if (state) {
            fragmentTvShowsBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvShowsBinding.progressBar.visibility = View.GONE
        }
    }

}
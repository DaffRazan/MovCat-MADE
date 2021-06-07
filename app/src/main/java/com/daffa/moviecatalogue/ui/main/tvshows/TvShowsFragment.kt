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
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.ui.TvShowsAdapter
import com.daffa.moviecatalogue.databinding.FragmentTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private var _binding: FragmentTvshowsBinding? = null
    private val fragmentTvShowsBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvshowsBinding.inflate(inflater, container, false)
        val view = fragmentTvShowsBinding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        with(fragmentTvShowsBinding.rvTvShow) {
            if (this.adapter != null) {
                this.adapter = null
            }
        }

        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TvShowsAdapter()

        showLoading(true)

        viewModel.getTvShows.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is com.daffa.moviecatalogue.core.data.source.Resource.Loading -> showLoading(
                        true
                    )
                    is com.daffa.moviecatalogue.core.data.source.Resource.Success -> {
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
                    is com.daffa.moviecatalogue.core.data.source.Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(context, "Something goes wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        fragmentTvShowsBinding.rvTvShow.layoutManager = LinearLayoutManager(context)
        fragmentTvShowsBinding.rvTvShow.setHasFixedSize(true)
        fragmentTvShowsBinding.rvTvShow.adapter = adapter
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
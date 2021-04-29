package com.daffa.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.repository.MainRepository.Companion.getInstance
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.databinding.FragmentMoviesBinding
import com.daffa.moviecatalogue.viewmodel.ViewModelFactory
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import com.daffa.moviecatalogue.viewmodels.MainViewModel_Factory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MoviesFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

    private lateinit var adapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val moviesAdapter = MoviesAdapter()

        with(fragmentMoviesBinding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        viewModel.getMovies.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<MovieResponse>) {
        when (resource) {
            is Resource.Loading -> fragmentMoviesBinding.isLoading = true
            is Resource.Empty -> fragmentMoviesBinding.isLoading = false
            is Resource.Success -> {
                fragmentMoviesBinding.isLoading = false
                resource.data.let { data -> adapter.data = data.results.toMutableList() }
            }
            is Resource.Error -> {
                fragmentMoviesBinding.isLoading = false
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
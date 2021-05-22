package com.daffa.moviecatalogue.ui.favorite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daffa.moviecatalogue.databinding.FragmentFavoriteBinding
import com.daffa.moviecatalogue.ui.favorite.SectionsPagerAdapter


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val favoriteBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        val view = favoriteBinding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity(), childFragmentManager)
        favoriteBinding.viewPager.adapter = sectionsPagerAdapter
        favoriteBinding.tabs.setupWithViewPager(favoriteBinding.viewPager)
    }
}
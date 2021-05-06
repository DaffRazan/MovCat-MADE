package com.daffa.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow
import com.daffa.moviecatalogue.databinding.ItemsTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.ui.movies.MoviesAdapter
import com.daffa.moviecatalogue.utils.Constants.API_POSTER_PATH

class TvShowsAdapter() : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    var data: List<TvShow> = arrayListOf()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    fun setTvShows(tvShows: List<TvShow>) {
        this.data = tvShows
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(private val binding: ItemsTvshowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(API_POSTER_PATH + tvShow.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
                tvTvShowTitle.text = tvShow.name
                tvTvShowReleaseDate.text = tvShow.first_air_date
                tvTvShowRating.text = tvShow.vote_average.toString()
            }
            itemView.setOnClickListener { onItemClickCallback.onItemClicked(tvShow.id.toString()) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowsAdapter.TvShowsViewHolder {
        val itemsTvshowsBinding =
            ItemsTvshowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsTvshowsBinding)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.TvShowsViewHolder, position: Int) {
        val tvShows = data[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = data.size


}
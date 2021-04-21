package com.daffa.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.databinding.ItemsMoviesBinding
import com.daffa.moviecatalogue.databinding.ItemsTvshowsBinding
import com.daffa.moviecatalogue.ui.detail.DetailFilmActivity
import com.daffa.moviecatalogue.ui.movies.MoviesAdapter

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    private var listTvShows = ArrayList<FilmEntity>()

    fun setTvShows(tvShows: List<FilmEntity>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    class TvShowsViewHolder(private val binding: ItemsTvshowsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: FilmEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(tvShow.imgPoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
                tvTvShowTitle.text = tvShow.title
                tvTvShowReleaseDate.text = tvShow.releaseDate

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_DATA, tvShow)
                    intent.putExtra(DetailFilmActivity.EXTRA_TYPE, "TV Show")
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowsAdapter.TvShowsViewHolder {
        val itemsTvshowsBinding =
            ItemsTvshowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsAdapter.TvShowsViewHolder(itemsTvshowsBinding)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.TvShowsViewHolder, position: Int) {
        val tvShows = listTvShows[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShows.size


}
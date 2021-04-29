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

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

//    private var listTvShows = ArrayList<FilmEntity>()

    var data: MutableList<TvShow> = ArrayList()

//    fun setTvShows(tvShows: List<FilmEntity>?) {
//        if (tvShows == null) return
//        this.listTvShows.clear()
//        this.listTvShows.addAll(tvShows)
//    }

    class TvShowsViewHolder(private val binding: ItemsTvshowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(tvShow.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
                tvTvShowTitle.text = tvShow.name
                tvTvShowReleaseDate.text = tvShow.first_air_date

//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
//                    intent.putExtra(DetailFilmActivity.EXTRA_DATA, tvShow)
//                    intent.putExtra(DetailFilmActivity.EXTRA_TYPE, "TV Show")
//                    itemView.context.startActivity(intent)
//                }

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
        val tvShows = data[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = data.size


}
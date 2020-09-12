package dev.falcer.moviedb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import dev.falcer.moviedb.BuildConfig
import dev.falcer.moviedb.R
import dev.falcer.moviedb.data.model.Movie
import kotlinx.android.synthetic.main.card_upcoming_movie.view.*

class UpComingMovieAdapter : RecyclerView.Adapter<UpComingMovieAdapter.ViewHolder>() {

    private lateinit var dataList: List<Movie>

    fun setData(data: List<Movie>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_upcoming_movie, parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Movie) {
            itemView.iv_movie.load("${BuildConfig.API_IMAGE}${data.backdropPath}") {
                    allowHardware(false)
                    crossfade(true)
                }

            itemView.tv_title.text = data.originalTitle
            itemView.tv_genre.text = data.genreIds.toString()
        }
    }
}
package com.akhilsreekar.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhilsreekar.movieslist.databinding.SimilarmovieItemBinding
import com.akhilsreekar.movieslist.entities.similarmovies.SimilarMovie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.similarmovie_item.view.*

class SimilarMovieAdapter : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    var similarMoviesList: List<SimilarMovie> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding: SimilarmovieItemBinding =
            SimilarmovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.bind(similarMoviesList[position])
    }

    override fun getItemCount(): Int = similarMoviesList.size

    fun setList(similarMovies: List<SimilarMovie>) {
        similarMoviesList = similarMovies
        notifyDataSetChanged()
    }

    class SimilarMovieViewHolder(private val itemBinding: SimilarmovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(similarMovie: SimilarMovie) {
            val moviePosterUrl = POSTER_BASE_URL+similarMovie.posterPath
            Glide.with(itemBinding.root)
                .load(moviePosterUrl)
                .placeholder(R.drawable.movie_icon)
                .into(itemBinding.moviePoster)
            itemBinding.title.text = similarMovie.title
        }
    }
}
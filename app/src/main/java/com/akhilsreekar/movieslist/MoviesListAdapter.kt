package com.akhilsreekar.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akhilsreekar.movieslist.databinding.MovieItemBinding
import com.akhilsreekar.movieslist.entities.currentplaying.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesListAdapter(private val listener:BookClickListener) : PagingDataAdapter<Movie,MoviesListAdapter.MovieViewHolder>(MovieComparator) {

    interface BookClickListener{
        fun onBookClicked(id:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding:MovieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listener,getItem(position)!!)
    }

    class MovieViewHolder(private val itemBinding: MovieItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(listener:BookClickListener,movie: Movie){
            itemBinding.movieTitle.text = movie.title
            itemBinding.releaseDate.text = movie.releaseDate

            val moviePosterUrl = POSTER_BASE_URL+movie.posterPath
            Glide.with(itemView.context)
                .load(moviePosterUrl)
                .placeholder(R.drawable.movie_icon)
                .into(itemView.movie_poster)

            itemBinding.book.setOnClickListener {
                listener.onBookClicked(movie.id)
            }
        }
    }


    companion object MovieComparator:DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }
    }
}

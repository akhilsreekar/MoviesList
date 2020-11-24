package com.akhilsreekar.movieslist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.text.input.textInputServiceFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhilsreekar.movieslist.POSTER_BASE_URL
import com.akhilsreekar.movieslist.R
import com.akhilsreekar.movieslist.ReviewAdapter
import com.akhilsreekar.movieslist.SimilarMovieAdapter
import com.akhilsreekar.movieslist.databinding.FragmentMovieDetailBinding
import com.akhilsreekar.movieslist.databinding.FragmentMovieListBinding
import com.akhilsreekar.movieslist.entities.MovieDetail
import com.akhilsreekar.movieslist.viewmodels.MovieDetailViewModel
import com.akhilsreekar.movieslist.viewmodels.MovieListViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_item.view.*

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var reviewAdapter:ReviewAdapter
    private lateinit var similarMovieAdapter: SimilarMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let {
            movieDetailViewModel.setMovieId(it)
        }

        fetchData()
        setUpObservers()
    }

    private fun setUpObservers(){
        movieDetailViewModel.message.observe(viewLifecycleOwner,  {
            it.getContentIfNotHandled()?.let{
                Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
            }
        })

        movieDetailViewModel.movieDetailLiveData.observe(viewLifecycleOwner,{
            if(it == null){
                setUpErrorView()
            }else {
                setUpViews(it)
            }
        })
    }

    private fun setUpErrorView(){
        binding.loadStateProgress.visibility = View.GONE
        binding.loadStateRetry.visibility = View.VISIBLE
    }

    private fun fetchData(){
        binding.loadStateProgress.visibility = View.VISIBLE
        movieDetailViewModel.fetchData()
    }

    private fun setUpViews(movieDetail:MovieDetail){
        binding.loadStateProgress.visibility = View.GONE
        binding.loadStateRetry.visibility = View.GONE

        val moviePosterUrl = POSTER_BASE_URL +movieDetail.synopsisResponse.posterPath
        Glide.with(requireContext())
            .load(moviePosterUrl)
            .placeholder(R.drawable.movie_icon)
            .into(binding.moviePoster)
        binding.movieName.contentTitle.text = "Name: "
        binding.movieName.content.text = movieDetail.synopsisResponse.title

        binding.popoularity.contentTitle.text = "Popularity: "
        binding.popoularity.content.text = movieDetail.synopsisResponse.popularity.toString()

        binding.genres.contentTitle.text = "Genres: "
        binding.genres.content.text = movieDetail.synopsisResponse.genres.map { it.name }.joinToString()

        binding.cast.contentTitle.text = "Cast: "
        binding.cast.content.text = movieDetail.creditResponse.cast.map { it.name }.joinToString()

        binding.crew.contentTitle.text = "Crew: "
        binding.crew.content.text = movieDetail.creditResponse.crew.map { it.name }.joinToString()


        reviewAdapter = ReviewAdapter()
        similarMovieAdapter = SimilarMovieAdapter()

        binding.reviews.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = reviewAdapter
        }

        binding.similarMovies.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = similarMovieAdapter
        }

        if(!movieDetail.reviewResponse.reviews.isNullOrEmpty()){
            binding.reviews.root.visibility = View.VISIBLE
            binding.reviews.contentTitle.text = "Reviews: "
            reviewAdapter.setList(movieDetail.reviewResponse.reviews)
        }else{
            binding.reviews.root.visibility = View.GONE
        }

        if(!movieDetail.similarMovieResponse.similarMovies.isNullOrEmpty()){
            binding.similarMovies.root.visibility = View.VISIBLE
            binding.similarMovies.contentTitle.text = "Similar Movies: "
            similarMovieAdapter.setList(movieDetail.similarMovieResponse.similarMovies)
        }else{
            binding.similarMovies.root.visibility = View.GONE
        }

    }




}
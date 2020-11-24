package com.akhilsreekar.movieslist.network

import com.akhilsreekar.movieslist.entities.credits.CreditResponse
import com.akhilsreekar.movieslist.entities.currentplaying.MovieResponse
import com.akhilsreekar.movieslist.entities.review.ReviewResponse
import com.akhilsreekar.movieslist.entities.similarmovies.SimilarMovieResponse
import com.akhilsreekar.movieslist.entities.synopsis.SynopsisResponse
import javax.inject.Inject

class ApiHelper @Inject constructor(val movieApiService: MovieApiService) : BaseDataSource() {

    suspend fun getNowPlayingMovies(language: String, page: Int): Resource<MovieResponse> =
        getResult { movieApiService.getNowPlayingMovies(language, page) }

    suspend fun getMovieSynopsis(language: String, id: Int): SynopsisResponse =
        movieApiService.getMovieSynopsis(id, language)

    suspend fun getMovieReviews(language: String, id: Int): ReviewResponse =
        movieApiService.getMovieReviews(id, language)

    suspend fun getMovieCredits(language: String, id: Int): CreditResponse =
        movieApiService.getMovieCredits(id, language)

    suspend fun getSimilarMovies(language: String, id: Int): SimilarMovieResponse =
        movieApiService.getSimilarMovies(id, language)
}
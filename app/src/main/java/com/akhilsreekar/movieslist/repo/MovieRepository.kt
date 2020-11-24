package com.akhilsreekar.movieslist.repo

import com.akhilsreekar.movieslist.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(val apiHelper: ApiHelper) {

    suspend fun getPopularMovies(language:String, page:Int) =  apiHelper.getNowPlayingMovies(language,page)

    suspend fun getMovieSynopsis(language: String, id: Int) = apiHelper.getMovieSynopsis(language,id)

    suspend fun getMovieReviews(language: String, id: Int) = apiHelper.getMovieReviews(language,id)

    suspend fun getMovieCredits(language: String, id: Int) = apiHelper.getMovieCredits(language,id)

    suspend fun getSimilarMovies(language: String, id: Int) = apiHelper.getSimilarMovies(language,id)

}
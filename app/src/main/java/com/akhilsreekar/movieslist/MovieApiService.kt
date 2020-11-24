package com.akhilsreekar.movieslist

import com.akhilsreekar.movieslist.entities.credits.CreditResponse
import com.akhilsreekar.movieslist.entities.currentplaying.MovieResponse
import com.akhilsreekar.movieslist.entities.review.ReviewResponse
import com.akhilsreekar.movieslist.entities.similarmovies.SimilarMovieResponse
import com.akhilsreekar.movieslist.entities.synopsis.SynopsisResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("language") language:String, @Query("page") page:Int): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieSynopsis(@Path("movie_id") id:Int,@Query("language") language:String) : SynopsisResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") id:Int,@Query("language") language:String) : ReviewResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") id:Int,@Query("language") language:String) : CreditResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") id:Int,@Query("language") language:String) : SimilarMovieResponse

}
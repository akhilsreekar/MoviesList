package com.akhilsreekar.movieslist.entities.similarmovies


import com.google.gson.annotations.SerializedName

data class SimilarMovieResponse(
    val page: Int,
    @SerializedName("results")
    val similarMovies: List<SimilarMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
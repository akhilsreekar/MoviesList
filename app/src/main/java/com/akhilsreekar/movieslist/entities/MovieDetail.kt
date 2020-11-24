package com.akhilsreekar.movieslist.entities

import com.akhilsreekar.movieslist.entities.credits.CreditResponse
import com.akhilsreekar.movieslist.entities.review.ReviewResponse
import com.akhilsreekar.movieslist.entities.similarmovies.SimilarMovieResponse
import com.akhilsreekar.movieslist.entities.synopsis.SynopsisResponse

data class MovieDetail (
    var id:Int,
    var synopsisResponse: SynopsisResponse,
    val reviewResponse:ReviewResponse,
    val creditResponse: CreditResponse,
    val similarMovieResponse: SimilarMovieResponse
)
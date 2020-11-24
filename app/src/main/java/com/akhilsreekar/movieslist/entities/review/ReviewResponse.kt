package com.akhilsreekar.movieslist.entities.review


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val id: Int,
    val page: Int,
    @SerializedName("results")
    val reviews: List<Review>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
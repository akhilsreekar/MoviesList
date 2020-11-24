package com.akhilsreekar.movieslist.entities.review


import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: Any,
    val name: String,
    val rating: Int,
    val username: String
)
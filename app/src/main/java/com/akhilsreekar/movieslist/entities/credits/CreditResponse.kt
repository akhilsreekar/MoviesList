package com.akhilsreekar.movieslist.entities.credits


import com.google.gson.annotations.SerializedName

data class CreditResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
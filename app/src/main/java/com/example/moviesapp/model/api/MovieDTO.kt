package com.example.moviesapp.model.api

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val id: Int,
    val title: String,
    @SerializedName("vote_average")
    val vote: Float,
    val overview: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("release_date")
    val date: String
) {
}
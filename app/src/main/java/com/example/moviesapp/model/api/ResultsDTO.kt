package com.example.moviesapp.model.api

import com.google.gson.annotations.SerializedName

data class ResultsDTO(
    @SerializedName("results")
    val movieList: List<MovieDTO>
) {
}
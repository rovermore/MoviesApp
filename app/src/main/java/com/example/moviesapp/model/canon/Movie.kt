package com.example.moviesapp.model.canon


data class Movie(
    val id: Int,
    val title: String,
    val vote: Float,
    val overview: String,
    val image: String,
    val date: String
) {
}
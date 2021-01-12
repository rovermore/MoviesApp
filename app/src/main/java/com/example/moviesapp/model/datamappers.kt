package com.example.moviesapp.model

import com.example.moviesapp.model.api.MovieDTO
import com.example.moviesapp.model.canon.Movie

fun MovieDTO.toMovie() = Movie(
    id,
    title,
    vote,
    overview,
    image,
    date
)
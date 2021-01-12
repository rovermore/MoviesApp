package com.example.moviesapp.repository

import com.example.moviesapp.model.canon.Movie


interface Repository {

    suspend fun getPopularMovies(): List<Movie>?
    suspend fun getMovieById(id: Int): Movie?
}
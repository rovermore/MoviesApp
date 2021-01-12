package com.example.moviesapp.client

import com.example.moviesapp.model.canon.Movie


interface ApiDataSource {

    suspend fun getPopularMovies(): List<Movie>?
    suspend fun getMovieById(id: Int): Movie?

}
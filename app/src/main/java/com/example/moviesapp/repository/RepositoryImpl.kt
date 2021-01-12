package com.example.moviesapp.repository

import com.example.moviesapp.client.RetrofitDataSource
import com.example.moviesapp.model.canon.Movie
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val retrofitDataSource: RetrofitDataSource): Repository {
    override suspend fun getPopularMovies(): List<Movie>? {
        return retrofitDataSource.getPopularMovies()
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return retrofitDataSource.getMovieById(id)
    }

}
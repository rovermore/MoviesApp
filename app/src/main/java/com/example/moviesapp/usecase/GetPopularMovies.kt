package com.example.moviesapp.usecase

import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.repository.RepositoryImpl
import javax.inject.Inject

class GetPopularMovies
    @Inject constructor(private val repositoryImpl: RepositoryImpl): UseCase<List<Movie>?>{

    override suspend fun request(): List<Movie>? =
        repositoryImpl.getPopularMovies()
}
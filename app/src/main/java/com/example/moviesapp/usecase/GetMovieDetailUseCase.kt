package com.example.moviesapp.usecase

import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.repository.RepositoryImpl
import javax.inject.Inject

class GetMovieDetailUseCase
@Inject constructor(private val repositoryImpl: RepositoryImpl): UseCaseWithParameter<Movie?, Int> {

    override suspend fun requestWithParameter(p: Int): Movie? =
        repositoryImpl.getMovieById(p)
}
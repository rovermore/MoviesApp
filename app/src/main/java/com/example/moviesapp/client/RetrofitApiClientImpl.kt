package com.example.moviesapp.client

import com.example.moviesapp.model.api.MovieDTO
import com.example.moviesapp.model.api.ResultsDTO
import javax.inject.Inject

class RetrofitApiClientImpl
    @Inject constructor(private val retrofitApiClient: RetrofitApiClient):
    RetrofitApiClient {

    override suspend fun getPopularMovies(apiKey: String): ResultsDTO? {
        return retrofitApiClient.getPopularMovies(apiKey)
    }

    override suspend fun getMovieById(id: Int, apiKey: String): MovieDTO? {
        return retrofitApiClient.getMovieById(id, apiKey)
    }

}
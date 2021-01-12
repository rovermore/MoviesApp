package com.example.moviesapp.client

import com.example.moviesapp.model.api.MovieDTO
import com.example.moviesapp.model.api.ResultsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApiClient {

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String)
            : ResultsDTO?

    @GET("{id}")
    suspend fun getMovieById(@Path("id") id: Int,
                             @Query("api_key") apiKey: String): MovieDTO?

}
package com.example.moviesapp.client


import com.example.moviesapp.BuildConfig
import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.model.toMovie
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(private val retrofitApiClient: RetrofitApiClientImpl): ApiDataSource {

    override suspend fun getPopularMovies(): List<Movie>? {
        return convertResultToMovieList()
    }

    private suspend fun convertResultToMovieList(): List<Movie>? {
        val movieList = mutableListOf<Movie>()
        val movieDTOList = retrofitApiClient.getPopularMovies(BuildConfig.API_KEY)?.movieList
        if (movieDTOList != null) {
            for (movieDTO in movieDTOList)
                movieList.add(movieDTO.toMovie())
        }
        return movieList
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return retrofitApiClient.getMovieById(id, BuildConfig.API_KEY)?.toMovie()
    }

}
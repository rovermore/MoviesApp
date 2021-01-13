package com.example.moviesapp

import com.example.moviesapp.model.toMovie
import junit.framework.TestCase
import org.junit.Test

class DatamappersTest {

    private val movieDTO = MovieApiMock.movieApi
    private val movieMock = MovieMock.movie

    @Test
    fun `movieApiDTO to movie`() {
        val movie = movieDTO.toMovie()
        TestCase.assertEquals(movie, movieMock)
    }
}
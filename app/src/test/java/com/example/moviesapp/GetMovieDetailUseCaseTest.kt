package com.example.moviesapp

import com.example.moviesapp.repository.RepositoryImpl
import com.example.moviesapp.usecase.GetMovieDetailUseCase
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetMovieDetailUseCaseTest {

    lateinit var repository: RepositoryImpl
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    private val movie = MovieMock.movie

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(RepositoryImpl::class.java)
        whenever(repository.getMovieById(1)).thenReturn(this@GetMovieDetailUseCaseTest.movie)
        getMovieDetailUseCase = GetMovieDetailUseCase(repository)
    }

    @Test
    fun `if ApiRepository return movie then GetMovieDetailUseCase returns same movie`() = runBlockingTest {
        val movieFromUseCase = getMovieDetailUseCase.requestWithParameter(1)
        Assert.assertEquals(movieFromUseCase, this@GetMovieDetailUseCaseTest.movie)
        Assert.assertEquals(movieFromUseCase!!.id, this@GetMovieDetailUseCaseTest.movie.id)
        Assert.assertEquals(movieFromUseCase.image, this@GetMovieDetailUseCaseTest.movie.image)
        Assert.assertEquals(movieFromUseCase.title, this@GetMovieDetailUseCaseTest.movie.title)
        Assert.assertEquals(movieFromUseCase.date, this@GetMovieDetailUseCaseTest.movie.date)
        Assert.assertEquals(movieFromUseCase.overview, this@GetMovieDetailUseCaseTest.movie.overview)
        Assert.assertEquals(movieFromUseCase.vote, this@GetMovieDetailUseCaseTest.movie.vote)

    }
}
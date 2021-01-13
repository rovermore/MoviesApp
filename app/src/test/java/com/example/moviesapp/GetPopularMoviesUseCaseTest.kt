package com.example.moviesapp

import com.example.moviesapp.repository.RepositoryImpl
import com.example.moviesapp.usecase.GetPopularMoviesUseCase
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPopularMoviesUseCaseTest {

    lateinit var repository: RepositoryImpl
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private val movieList = MovieMock.movieList

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(RepositoryImpl::class.java)
        whenever(repository.getPopularMovies()).thenReturn(this@GetPopularMoviesUseCaseTest.movieList)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
    }

    @Test
    fun `if ApiRepository return movieList then GetPopularMoviesUseCase returns same movieList`() = runBlockingTest {
        val movieListFromUseCase = getPopularMoviesUseCase.request()
        Assert.assertEquals(movieListFromUseCase, this@GetPopularMoviesUseCaseTest.movieList)
        Assert.assertEquals(movieListFromUseCase!![0].id, this@GetPopularMoviesUseCaseTest.movieList[0].id)
        Assert.assertEquals(movieListFromUseCase[0].image, this@GetPopularMoviesUseCaseTest.movieList[0].image)
        Assert.assertEquals(movieListFromUseCase[0].title, this@GetPopularMoviesUseCaseTest.movieList[0].title)
        Assert.assertEquals(movieListFromUseCase[0].date, this@GetPopularMoviesUseCaseTest.movieList[0].date)
        Assert.assertEquals(movieListFromUseCase[0].overview, this@GetPopularMoviesUseCaseTest.movieList[0].overview)
        Assert.assertEquals(movieListFromUseCase[0].vote, this@GetPopularMoviesUseCaseTest.movieList[0].vote)

    }

}
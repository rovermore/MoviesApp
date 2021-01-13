package com.example.moviesapp

import com.example.moviesapp.client.RetrofitDataSource
import com.example.moviesapp.repository.RepositoryImpl
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryImplTest {

    private lateinit var retrofitDataSource: RetrofitDataSource
    private lateinit var repository: RepositoryImpl

    private val movieListResult = MovieMock.movieList
    private val movieResult = MovieMock.movie

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        retrofitDataSource = Mockito.mock(RetrofitDataSource::class.java)
        whenever(retrofitDataSource.getPopularMovies()).thenReturn(this@RepositoryImplTest.movieListResult)
        whenever(retrofitDataSource.getMovieById(1)).thenReturn(this@RepositoryImplTest.movieResult)
        repository = RepositoryImpl(retrofitDataSource)
    }


    @Test
    fun `if RetrofitDataSource return movieList then RepositoryImpl calls getPopularMovies method and returns movieList`() = runBlockingTest {
        val movieListFromDataSource = repository.getPopularMovies()
        Assert.assertEquals(movieListFromDataSource, this@RepositoryImplTest.movieListResult)
        Assert.assertEquals(movieListFromDataSource!![0].id, this@RepositoryImplTest.movieListResult[0].id)
        Assert.assertEquals(movieListFromDataSource[0].image, this@RepositoryImplTest.movieListResult[0].image)
        Assert.assertEquals(movieListFromDataSource[0].title, this@RepositoryImplTest.movieListResult[0].title)
        Assert.assertEquals(movieListFromDataSource[0].date, this@RepositoryImplTest.movieListResult[0].date)
        Assert.assertEquals(movieListFromDataSource[0].overview, this@RepositoryImplTest.movieListResult[0].overview)
        Assert.assertEquals(movieListFromDataSource[0].vote, this@RepositoryImplTest.movieListResult[0].vote)
    }

    @Test
    fun `if RetrofitDataSource return movie then RepositoryImpl calls getMovieById method returns same movie`() = runBlockingTest {
        val movieFromDataSource = repository.getMovieById(1)
        Assert.assertEquals(movieFromDataSource, this@RepositoryImplTest.movieResult)
        Assert.assertEquals(movieFromDataSource!!.id, this@RepositoryImplTest.movieResult.id)
        Assert.assertEquals(movieFromDataSource.image, this@RepositoryImplTest.movieResult.image)
        Assert.assertEquals(movieFromDataSource.title, this@RepositoryImplTest.movieResult.title)
        Assert.assertEquals(movieFromDataSource.date, this@RepositoryImplTest.movieResult.date)
        Assert.assertEquals(movieFromDataSource.overview, this@RepositoryImplTest.movieResult.overview)
        Assert.assertEquals(movieFromDataSource.vote, this@RepositoryImplTest.movieResult.vote)
    }
}
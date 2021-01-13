package com.example.moviesapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.screen.main.MainViewModel
import com.example.moviesapp.usecase.GetPopularMoviesUseCase
import com.example.moviesapp.utils.ViewState
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var mainViewModel: MainViewModel

    private val movieList = MovieMock.movieList
    private val emptyMovieList = MovieMock.emptyMovieList

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        getPopularMoviesUseCase = Mockito.mock(GetPopularMoviesUseCase::class.java)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `checks viewState is error when request is null`() = runBlocking {
        whenever(getPopularMoviesUseCase.request()).thenReturn(null)
        mainViewModel = MainViewModel(getPopularMoviesUseCase)
        Truth.assertThat(mainViewModel.viewState.value).isEqualTo(ViewState.Error)
    }

    @Test
    fun `checks viewState is error when request is empty`() = runBlocking {
        whenever(getPopularMoviesUseCase.request()).thenReturn(emptyMovieList)
        mainViewModel = MainViewModel(getPopularMoviesUseCase)
        Truth.assertThat(mainViewModel.viewState.value).isEqualTo(ViewState.Error)
    }

    @Test
    fun `checks viewState is not empty when request is not empty`() = runBlocking {
        whenever(getPopularMoviesUseCase.request()).thenReturn(movieList)
        mainViewModel = MainViewModel(getPopularMoviesUseCase)
        Truth.assertThat(mainViewModel.viewState.value).isEqualTo(ViewState.Content(movieList))
    }
}
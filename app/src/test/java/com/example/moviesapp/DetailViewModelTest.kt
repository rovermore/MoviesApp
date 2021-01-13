package com.example.moviesapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.screen.detail.DetailViewModel
import com.example.moviesapp.usecase.GetMovieDetailUseCase
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
class DetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase
    private lateinit var detailViewModel: DetailViewModel

    private val movie = MovieMock.movie

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        getMovieDetailUseCase = Mockito.mock(GetMovieDetailUseCase::class.java)
        detailViewModel = DetailViewModel(getMovieDetailUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `checks viewState is not empty when request is not null`() = runBlocking {
        whenever(getMovieDetailUseCase.requestWithParameter(1)).thenReturn(movie)
        detailViewModel.initialize(1)
        Truth.assertThat(detailViewModel.viewState.value).isEqualTo(ViewState.Content(movie))
    }

    @Test
    fun `checks viewState is error when request is null`() = runBlocking {
        whenever(getMovieDetailUseCase.requestWithParameter(1)).thenReturn(null)
        detailViewModel.initialize(1)
        Truth.assertThat(detailViewModel.viewState.value).isEqualTo(ViewState.Error)
    }
}
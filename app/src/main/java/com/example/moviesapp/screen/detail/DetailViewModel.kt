package com.example.moviesapp.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.usecase.GetMovieDetailUseCase
import com.example.moviesapp.utils.ViewState
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DetailViewModel
    @Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<Movie>>()
    val viewState: LiveData<ViewState<Movie>>
        get() = _viewState

    fun initialize(movieId: Int) {
        loadData(movieId)
    }

    private fun loadData(movieId: Int) {
        _viewState.value = ViewState.Loading
        observeResponse(movieId)
    }

    private fun observeResponse(movieId: Int) {
        viewModelScope.launch {
            try {
                val movie = getMovieDetailUseCase.requestWithParameter(movieId)
                if (movie != null)
                    _viewState.value = ViewState.Content(movie)
                else
                    _viewState.value = ViewState.Error
            } catch (e: Exception) {
                _viewState.value = ViewState.Error
            }
        }
    }
}
package com.example.moviesapp.screen.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.usecase.GetPopularMovies
import com.example.moviesapp.utils.ViewState
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val getPopularMoviesUseCase: GetPopularMovies) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<List<Movie>>>()
    val viewState: LiveData<ViewState<List<Movie>>>
        get() = _viewState

    init {
        loadData()
    }

    fun loadData() {
        _viewState.value = ViewState.Loading
        observeResponse()
    }

    private fun observeResponse() {
        viewModelScope.launch {
            try {
                val popularMovies = getPopularMoviesUseCase.request()
                if (!popularMovies.isNullOrEmpty())
                    _viewState.value = ViewState.Content(popularMovies)
                else
                    _viewState.value = ViewState.Error
            } catch (e: Exception) {
                _viewState.value = ViewState.Error
            }
        }
    }

}
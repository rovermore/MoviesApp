package com.example.moviesapp.utils

sealed class ViewState<out T> {
    object Loading: ViewState<Nothing>()
    object Error: ViewState<Nothing>()
    data class Content<T>(val data: T): ViewState<T>()
}
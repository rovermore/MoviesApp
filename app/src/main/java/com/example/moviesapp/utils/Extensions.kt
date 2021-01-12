package com.example.moviesapp.utils

import android.view.View
import com.example.moviesapp.model.canon.Movie

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Movie.getImageUrl(): String {
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342/"
    return IMAGE_BASE_URL.plus(this.image)
}
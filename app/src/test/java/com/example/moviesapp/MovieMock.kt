package com.example.moviesapp

import com.example.moviesapp.model.canon.Movie

object MovieMock {

    val movieList = listOf<Movie>(
        Movie(
            508442,
            "Wonder Woman 1984",
            7.2f,
            "Joe Gardner is a middle school teacher ",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-12-16"
        ),
        Movie(
            508552,
            "Cosmoball",
            5.2f,
            "Joe Gardner is Lorem ipsum dolor eacher",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-11-11"
        ),
        Movie(
            608442,
            "Soul",
            8.4f,
            "lorem ipsum school teacher ",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-10-10"
        )
    )

    val movie = Movie(
        608442,
        "Soul",
        8.4f,
        "lorem ipsum school teacher ",
        "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        "2020-10-10"
    )

    val emptyMovieList = listOf<Movie>()

}
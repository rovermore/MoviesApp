package com.example.moviesapp

import com.example.moviesapp.model.api.MovieDTO
import com.example.moviesapp.model.api.ResultsDTO

object MovieApiMock {

    val movieApiList = listOf<MovieDTO>(
        MovieDTO(
            508442,
            "Wonder Woman 1984",
            7.2f,
            "Joe Gardner is a middle school teacher ",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-12-16"
        ),
        MovieDTO(
            508552,
            "Cosmoball",
            5.2f,
            "Joe Gardner is Lorem ipsum dolor eacher",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-11-11"
        ),
        MovieDTO(
            608442,
            "Soul",
            8.4f,
            "lorem ipsum school teacher ",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-10-10"
        )
    )

    val movieApi = MovieDTO(
        608442,
        "Soul",
        8.4f,
        "lorem ipsum school teacher ",
        "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        "2020-10-10"
    )

    val resultsDTO = ResultsDTO(movieApiList)
}
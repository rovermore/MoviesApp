package com.example.moviesapp.usecase

interface UseCaseWithParameter<T, P> {
    suspend fun  requestWithParameter(p: P): T
}
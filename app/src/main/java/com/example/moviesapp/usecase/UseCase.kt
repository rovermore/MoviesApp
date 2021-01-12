package com.example.moviesapp.usecase

interface UseCase<T> {

    suspend fun request(): T

}
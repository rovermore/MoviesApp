package com.example.moviesapp.injection


import com.example.moviesapp.client.RetrofitDataSource
import com.example.moviesapp.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun repository(retrofitDataSource: RetrofitDataSource
    ): RepositoryImpl =
        RepositoryImpl(retrofitDataSource)
}
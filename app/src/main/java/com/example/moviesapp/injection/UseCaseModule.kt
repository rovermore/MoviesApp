package com.example.moviesapp.injection




import com.example.moviesapp.repository.RepositoryImpl
import com.example.moviesapp.usecase.GetMovieDetailUseCase
import com.example.moviesapp.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getPopularMoviesUseCase(repositoryImpl: RepositoryImpl): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repositoryImpl)

    @Provides
    fun getMovieDetailUseCase(repositoryImpl: RepositoryImpl): GetMovieDetailUseCase =
        GetMovieDetailUseCase(repositoryImpl)


}
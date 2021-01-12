package com.example.moviesapp.injection




import com.example.moviesapp.repository.RepositoryImpl
import com.example.moviesapp.usecase.GetMovieDetailUseCase
import com.example.moviesapp.usecase.GetPopularMovies
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getPopularMoviesUseCase(repositoryImpl: RepositoryImpl): GetPopularMovies =
        GetPopularMovies(repositoryImpl)

    @Provides
    fun getMovieDetailUseCase(repositoryImpl: RepositoryImpl): GetMovieDetailUseCase =
        GetMovieDetailUseCase(repositoryImpl)


}
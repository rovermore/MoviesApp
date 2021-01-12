package com.example.moviesapp.injection

import com.example.moviesapp.screen.detail.DetailFragment
import com.example.moviesapp.screen.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)
}
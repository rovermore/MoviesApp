package com.example.moviesapp.injection

import android.content.Context
import com.example.moviesapp.MoviesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MoviesApp) {

    @Provides
    @Singleton
    fun context(): Context = app.applicationContext

}
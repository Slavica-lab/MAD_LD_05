package com.example.movieappmad23.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad23.repositories.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            return MoviesViewModel(repository = repository) as T
        }
        if(modelClass.isAssignableFrom(DetailScreenViewModel::class.java)){
            return DetailScreenViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
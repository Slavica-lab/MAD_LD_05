package com.example.movieappmad23.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailScreenViewModel(repository: MovieRepository): ViewModel() {
    var _repository = repository
    var _movie = mutableStateOf(Movie())

    val movie: Movie
        get()=_movie.value

    fun filterMovie(movieId: String) {
        viewModelScope.launch {
            _repository.getById(movieId).collect { _movie.value = it ?: Movie() };
        }
    }

    fun toggleFavorite(movie: Movie){
        movie.favorite.value = !movie.favorite.value
        viewModelScope.launch {
            _repository.updateMovie(movie)
        }
    }
}
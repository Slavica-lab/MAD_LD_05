package com.example.movieappmad23.ViewModel

import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableList()
    val movieList: List<Movie>
        get()=_movieList

    //write methods

    fun getFavoriteMovies(): List<Movie>{
        return _movieList.filter { it.isFavorite }
    }

    fun removeMovie(movie: Movie) {
        _movieList.remove(movie)
    }

    fun addMovie(movie: Movie) {
        _movieList.add(movie)
    }

}
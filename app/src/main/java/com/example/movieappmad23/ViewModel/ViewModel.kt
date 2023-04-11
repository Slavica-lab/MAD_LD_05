package com.example.movieappmad23.ViewModel

import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableList()
    val movieList: List<Movie>
        get()=_movieList
}
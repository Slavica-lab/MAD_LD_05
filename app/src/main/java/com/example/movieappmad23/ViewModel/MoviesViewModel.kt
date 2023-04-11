package com.example.movieappmad23.ViewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableList()
    val movieList: List<Movie>
        get()=_movieList

    //write methods

    fun getFavoriteMovies(): List<Movie>{
        return _movieList.filter { it.isFavorite.value }
    }

    fun removeMovie(movie: Movie) {
        _movieList.remove(movie)
    }

    fun addMovie(movie: Movie) {
        _movieList.add(movie)
    }

    fun filterMovie(movieId: String): Movie {
        return _movieList.filter { it.id == movieId}[0]
    }

    fun isTitleValid(title: String): Boolean {
        return title.isNotEmpty()
    }

    fun isYearValid(year: String): Boolean {
        return year.isNotEmpty()
    }

    fun isDirectorValid(director: String): Boolean {
        return director.isNotEmpty()
    }

    fun isActorValid(actor: String): Boolean {
        return actor.isNotEmpty()
    }

    fun isGenreSelectablesValid(genres: List <ListItemSelectable>): Boolean {
        return genres.any { it.isSelected }
    }

    fun isPlotValid(plot: String): Boolean {
        return !plot.isDigitsOnly()
    }

    fun isRatingValid(rating: String): Boolean {
        return rating.toFloatOrNull() != null
    }


}
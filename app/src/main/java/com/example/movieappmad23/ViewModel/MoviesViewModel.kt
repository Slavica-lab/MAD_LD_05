package com.example.movieappmad23.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.launch

class MoviesViewModel(repository: MovieRepository): ViewModel() {
    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{
                movies ->
                run {
                    _movieList.clear()
                    _movieList.addAll(movies)
                }
            }
        }
    }
    private var _repository = repository
    private var _movieList = mutableStateListOf<Movie>()
    val movieList: List<Movie>
        get()=_movieList

    //write methods

    fun getFavoriteMovies(): List<Movie>{
        var result = mutableStateListOf<Movie>()
        viewModelScope.launch {
            _repository.getFavoriteMovies().collect{
                    movies ->
                run {
                    result.clear()
                    result.addAll(movies)
                }
            }
        }
        return result
    }

    fun removeMovie(movie: Movie) {
        viewModelScope.launch {
            _repository.deleteMovie(movie)
        }
    }

    fun addMovie(movie: Movie) {
        viewModelScope.launch {
            _repository.addMovie(movie)
        }
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

    fun toggleFavorite(movie: Movie){
        movie.favorite.value = !movie.favorite.value
        viewModelScope.launch {
            _repository.updateMovie(movie)
        }
    }


}
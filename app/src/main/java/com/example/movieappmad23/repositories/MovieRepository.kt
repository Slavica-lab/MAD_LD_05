package com.example.movieappmad23.repositories

import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun addMovie(movie: Movie) = movieDao.add(movie = movie)
    suspend fun updateMovie(movie: Movie) = movieDao.update(movie = movie)
    //suspend fun deleteMove(movie: Movie) = movieDao.delete(movie = movie)
    fun getAllMovies() : Flow<List<Movie>> = movieDao.getAll()
    //fun getFavoriteMoves() : Flow<List<Movie>> = movieDao.getFavorites()
    //fun getById(id: Long) : Flow<Movie?> = movieDao.get(id)
}
package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.ViewModel.DetailScreenViewModel
import com.example.movieappmad23.ViewModel.MoviesViewModel
import com.example.movieappmad23.ViewModel.MoviesViewModelFactory
import com.example.movieappmad23.databases.MovieDatabase
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = MoviesViewModelFactory(repository = repository)
    val moviesViewModel: MoviesViewModel = viewModel(factory = factory)
    val detailsViewModel: DetailScreenViewModel = viewModel(factory = factory)
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, moviesViewModel = moviesViewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, moviesViewModel = moviesViewModel)
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, moviesViewModel = moviesViewModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController, backStackEntry.arguments?.getString(
                DETAIL_ARGUMENT_KEY), moviesViewModel = detailsViewModel)   // get the argument from navhost that will be passed
        }
    }
}
package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.ViewModel.MoviesViewModel
import com.example.movieappmad23.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val moviesViewModel: MoviesViewModel = viewModel()
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
                DETAIL_ARGUMENT_KEY), moviesViewModel = moviesViewModel)   // get the argument from navhost that will be passed
        }
    }
}
package com.example.movieappmad23

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.movieappmad23.ViewModel.MoviesViewModel
import com.example.movieappmad23.navigation.Navigation
import com.example.movieappmad23.ui.theme.MovieAppMAD23Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD23Theme {
                Navigation()
            }
        }

        val moviesViewModel: MoviesViewModel by viewModels()
        moviesViewModel.movieList
    }

    override fun onStart() {
        super.onStart()
        Log.i("Mainactivity", "I am onStart")
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.i("Maincativity", "I am onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Maincativity", "I am onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Maincativity", "I am onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Maincativity", "I am onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Maincativity", "I am onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Maincativity", "I am onRestart")
    }
}


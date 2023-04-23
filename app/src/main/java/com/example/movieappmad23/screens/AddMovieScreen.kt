package com.example.movieappmad23.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.ViewModel.MoviesViewModel
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.widgets.SimpleTopAppBar
import java.time.Instant

@Composable
fun AddMovieScreen(navController: NavController, moviesViewModel: MoviesViewModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContentAdd(Modifier.padding(padding),
        moviesViewModel = moviesViewModel,
        navController = navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContentAdd(modifier: Modifier = Modifier, moviesViewModel: MoviesViewModel, navController: NavController) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(true)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = !moviesViewModel.isTitleValid(title)
            )

            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { year = it },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = !moviesViewModel.isYearValid(year)
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)
            if (!moviesViewModel.isGenreSelectablesValid(genreItems)){
                Text(text = "Please select at least one!")
            }

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { director = it },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = !moviesViewModel.isDirectorValid(director)
            )

            OutlinedTextField(
                value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { actors = it },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = !moviesViewModel.isActorValid(actors)
            )

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = !moviesViewModel.isPlotValid(plot)
            )

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                                rating = if(it.startsWith("0")) {
                                    ""
                                } else {
                                    it
                                }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = !moviesViewModel.isRatingValid(rating)
            )
            isEnabledSaveButton = moviesViewModel.isTitleValid(title)
                    && moviesViewModel.isYearValid(year)
                    && moviesViewModel.isGenreSelectablesValid(genreItems)
                    && moviesViewModel.isDirectorValid(director)
                    && moviesViewModel.isActorValid(actors)
                    && moviesViewModel.isPlotValid(plot)
                    && moviesViewModel.isRatingValid(rating)

            Button(
                enabled = isEnabledSaveButton,
                onClick = {
                    val newMovie = Movie(id = Instant.now().toString(),
                        title = title,
                        year = year,
                        genre = genreItems.filter { it.isSelected }.map {Genre.valueOf(it.title)}.toList(),
                        director = director,
                        actors = actors,
                        plot = plot,
                        images = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png"),
                        rating = rating.toFloat()
                    )
                    moviesViewModel.addMovie(newMovie)
                    navController.popBackStack()

                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}
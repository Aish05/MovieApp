package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieDetails
import com.example.movieapp.model.DataHolder.movieDetails
import com.example.movieapp.model.DataHolder.movieListResponse
import com.example.movieapp.model.MovieListResponse
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.util.readRawJsonFile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readRawJsonFile<MovieListResponse>(R.raw.movie_list_mock, this)?.let {
            movieListResponse = it.results
        }

        readRawJsonFile<MovieDetails>(R.raw.movie_details_mock, this)?.let {
            movieDetails = it
        }

        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppTheme() {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}
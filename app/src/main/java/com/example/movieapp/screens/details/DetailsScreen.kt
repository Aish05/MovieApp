package com.example.movieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.DataHolder
import com.example.movieapp.widgets.*

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val movieFromId = DataHolder.movieListResponse?.filter { it.id == movieId }
    val movieDetails = DataHolder.movieDetails

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Text(text = "Movies")
            }
        }
    }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(text = "${movieFromId?.get(0)?.title}", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Card(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    elevation = 5.dp,
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Column() {
                        MovieImages(movieDetails)
                        Spacer(modifier = Modifier.height(8.dp))
                        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                            movieDetails?.genre?.let { it1 -> ChipRow(it1) }
                            Spacer(modifier = Modifier.height(8.dp))
                            Plot(movieDetails?.plot)
                            Spacer(modifier = Modifier.height(8.dp))
                            movieDetails?.actors?.let { it1 -> Actor(it1) }
                            movieDetails?.rating?.let { Rating(it) }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Plot(plot: String?) {
    Column() {
        Text(
            text = "Overview:",
            style = MaterialTheme.typography.subtitle2
        )
        Text(text = plot.orEmpty(), style = MaterialTheme.typography.body2)
    }

}






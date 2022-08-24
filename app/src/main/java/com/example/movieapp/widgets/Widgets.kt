package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.movieapp.model.DataHolder
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieDetails
import com.example.movieapp.model.Rating

@Composable
fun MovieRow(movie: Movie = DataHolder.movieListResponse?.get(0)!!, onItemClick: (String) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()

            .clickable {
                onItemClick(movie.id.orEmpty())
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 5.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(data = movie.image, builder = {
                        crossfade(true)
                        transformations(RoundedCornersTransformation())
                    }),
                    contentDescription = "Movie poster"
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title.orEmpty(), style = MaterialTheme.typography.subtitle1)

                AnimatedVisibility(visible = expanded) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Imdb Rating")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(": ${movie.rating}")
                            }
                        }, modifier = Modifier.padding(6.dp))
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(
                            text = "Movie Actors: Mithila palkar",
                            style = MaterialTheme.typography.caption,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Movie Director: XYZ",
                            style = MaterialTheme.typography.caption,
                            fontSize = 13.sp
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }
        }

    }
}


@Composable
inline fun rememberAsyncImagePainter(data: Any?, builder: ImageRequest.Builder.() -> Unit) =
    rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data).apply(builder).build()
    )

@Composable
fun HorizontalActors(actors: List<String>) {
    Text(
        text = "Actors:",
        style = MaterialTheme.typography.subtitle2
    )
    Spacer(modifier = Modifier.height(2.dp))
    LazyRow {
        items(actors) {
            Text(
                text = "$it  ",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun MovieImages(movieDetails: MovieDetails?) {
    Image(
        painter = rememberAsyncImagePainter(model = movieDetails?.image),
        contentDescription = "Movie details image"
    )
}

@Composable
fun ChipRow(dataList: List<String>) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Genre:  ",
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center
        )
        LazyRow(modifier = Modifier.padding(top = 8.dp)) {
            items(items = dataList) {
                Surface(
                    modifier = Modifier.padding(end = 4.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Transparent,
                    border = BorderStroke(1.dp, color = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Actor(actors: List<String>) {
    Text(
        text = "Actors:",
        style = MaterialTheme.typography.subtitle2
    )
    Spacer(modifier = Modifier.height(2.dp))
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(modifier = Modifier.padding(top = 8.dp)) {
            items(items = actors) {
                Surface(
                    Modifier.clip(RoundedCornerShape(2.dp)).padding(5.dp),
                    border = BorderStroke(1.dp, color = Color.DarkGray),
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2
                    )
                }

            }
        }
    }
}

@Composable
fun ActorsRow(dataList: List<String>) {
    LazyRow(modifier = Modifier.padding(top = 8.dp)) {
        items(items = dataList) {
            Surface(
                modifier = Modifier.padding(end = 4.dp),
                shape = MaterialTheme.shapes.medium,
                color = Color.Transparent,
                border = BorderStroke(1.dp, color = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun Rating(rating: Rating) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Text(
            text = "Rating:",
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = "${rating.star}",
            style = MaterialTheme.typography.body2
        )
    }
}
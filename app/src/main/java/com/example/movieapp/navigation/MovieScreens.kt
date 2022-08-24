package com.example.movieapp.navigation

import java.lang.IllegalArgumentException

enum class MovieScreens {
    HOME,
    DETAILS;

    companion object {
        fun fromRoute(route : String?) : MovieScreens = when(route?.substringBefore("/")) {
            HOME.name -> HOME
            DETAILS.name -> DETAILS
            null -> HOME
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
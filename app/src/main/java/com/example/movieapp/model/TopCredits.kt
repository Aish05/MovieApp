package com.example.movieapp.model

import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
@JsonClass(generateAdapter = true)
data class TopCredits(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "value")
    val value: List<String?>? = null
)
package com.itis.joke.core.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class JokeListResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<JokeResponse>,
)
package com.itis.joke.core.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val error: Boolean? = null,
    val category: String,
    val type: String,
    val joke: String? = null,
    val setup: String? = null,
    val delivery: String? = null,
    val flags: Map<String, Boolean>,
    val safe: Boolean,
    val id: Int,
    val lang: String
)
package com.itis.joke.core.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class JokeSubmissionRequest(
    val formatVersion: Int,
    val category: String,
    val type: String,
    val joke: String?,
    val setup: String?,
    val discovery: String?,
    val flags: JokeFlags,
    val lang: String
)

@Serializable
data class JokeFlags(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)
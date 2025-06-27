package com.itis.joke.feature.random_joke.domain

import com.itis.joke.core.data.datasource.remote.model.JokeModel

interface RandomJokeRepository {
    suspend fun getJoke() : JokeModel
}
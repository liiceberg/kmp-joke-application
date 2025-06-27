package com.itis.joke.feature.random_joke.domain

import com.itis.joke.core.common.model.JokeModel

interface RandomJokeRepository {
    suspend fun getJoke() : JokeModel
}
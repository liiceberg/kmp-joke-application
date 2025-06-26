package com.itis.joke.feature.random_joke.domain

interface RandomJokeRepository {
    suspend fun getJoke() : RandomJokeModel
}
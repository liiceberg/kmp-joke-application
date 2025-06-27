package com.itis.joke.feature.suggest_joke.domain

interface SuggestJokeRepository {
    suspend fun sendJoke(joke: SubmitJokeModel)
}
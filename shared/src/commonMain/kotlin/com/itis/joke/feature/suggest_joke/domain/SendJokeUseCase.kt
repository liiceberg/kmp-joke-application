package com.itis.joke.feature.suggest_joke.domain

interface SendJokeUseCase {
    suspend operator fun invoke(joke: SubmitJokeModel)
}
package com.itis.joke.feature.random_joke.domain.usecase

import com.itis.joke.feature.random_joke.domain.RandomJokeModel

interface GetRandomJokeUseCase {
    suspend operator fun invoke() : RandomJokeModel
}
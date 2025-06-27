package com.itis.joke.feature.random_joke.domain.usecase

import com.itis.joke.core.common.model.JokeModel

interface GetRandomJokeUseCase {
    suspend operator fun invoke() : JokeModel
}
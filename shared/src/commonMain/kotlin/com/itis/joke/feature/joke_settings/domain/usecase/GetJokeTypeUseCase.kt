package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.joke.JokeType

interface GetJokeTypeUseCase {
    suspend operator fun invoke() : JokeType
}
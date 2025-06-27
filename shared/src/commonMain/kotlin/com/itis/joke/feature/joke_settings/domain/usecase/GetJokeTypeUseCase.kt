package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.model.JokeType

interface GetJokeTypeUseCase {
    suspend operator fun invoke() : JokeType
}
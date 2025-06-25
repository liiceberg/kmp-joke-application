package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.joke.JokeCategory

interface GetJokeCategoryUseCase {
    suspend operator fun invoke() : JokeCategory
}
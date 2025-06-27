package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.model.JokeCategory

interface GetJokeCategoryUseCase {
    suspend operator fun invoke() : JokeCategory
}
package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.joke.JokeBlackListItem

interface GetBlacklistUseCase {
    suspend operator fun invoke() : List<JokeBlackListItem>
}
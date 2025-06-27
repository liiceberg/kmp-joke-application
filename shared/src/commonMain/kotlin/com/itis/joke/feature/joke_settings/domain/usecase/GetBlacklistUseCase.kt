package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.model.JokeBlackListItem

interface GetBlacklistUseCase {
    suspend operator fun invoke() : List<JokeBlackListItem>
}
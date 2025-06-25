package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType

interface SetJokeSettingsUseCase {
    suspend operator fun invoke(type: JokeType, category: JokeCategory, blackList: List<JokeBlackListItem>)
}
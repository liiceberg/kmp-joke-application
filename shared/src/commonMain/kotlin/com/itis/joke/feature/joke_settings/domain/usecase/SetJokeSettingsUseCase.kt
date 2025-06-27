package com.itis.joke.feature.joke_settings.domain.usecase

import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.common.model.JokeType

interface SetJokeSettingsUseCase {
    suspend operator fun invoke(type: JokeType, category: JokeCategory, blackList: List<JokeBlackListItem>)
}
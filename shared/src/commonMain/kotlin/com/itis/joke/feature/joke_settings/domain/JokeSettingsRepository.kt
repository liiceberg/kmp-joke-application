package com.itis.joke.feature.joke_settings.domain

import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.common.model.JokeType

interface JokeSettingsRepository {
    suspend fun saveCategory(category: JokeCategory)
    suspend fun getCategory() : JokeCategory
    suspend fun saveType(type: JokeType)
    suspend fun getType() : JokeType
    suspend fun saveBlackList(list: List<JokeBlackListItem>)
    suspend fun getBlackList() : List<JokeBlackListItem>
}
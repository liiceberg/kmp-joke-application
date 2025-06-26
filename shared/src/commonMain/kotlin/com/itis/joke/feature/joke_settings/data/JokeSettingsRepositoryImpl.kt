package com.itis.joke.feature.joke_settings.data

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.data.datasource.local.JokeSettingsDataSource
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository

class JokeSettingsRepositoryImpl(
    private val dataSource: JokeSettingsDataSource,
) : JokeSettingsRepository {

    override suspend fun saveCategory(category: JokeCategory) {
        dataSource.saveCategory(category)
    }

    override suspend fun getCategory(): JokeCategory = dataSource.getCategory()

    override suspend fun saveType(type: JokeType) {
        dataSource.saveType(type)
    }

    override suspend fun getType(): JokeType = dataSource.getType()

    override suspend fun saveBlackList(list: List<JokeBlackListItem>) {
        dataSource.saveBlackList(list)
    }

    override suspend fun getBlackList(): List<JokeBlackListItem> = dataSource.getBlackList()
}
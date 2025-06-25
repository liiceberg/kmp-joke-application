package com.itis.joke.feature.joke_settings.data

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.data.settings.SettingsKeys
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository
import com.russhwolf.settings.Settings
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class JokeSettingsRepositoryImpl(
    private val settings: Settings,
) : JokeSettingsRepository {

    override suspend fun saveCategory(category: JokeCategory) {
        settings.putString(SettingsKeys.JOKE_CATEGORY, category.name)
    }

    override suspend fun getCategory(): JokeCategory {
        val value = settings.getStringOrNull(SettingsKeys.JOKE_CATEGORY)
        return if (value != null) {
            JokeCategory.valueOf(value)
        } else {
            JokeCategory.ANY
        }
    }

    override suspend fun saveType(type: JokeType) {
        settings.putString(SettingsKeys.JOKE_TYPE, type.name)
    }

    override suspend fun getType(): JokeType {
        val value = settings.getStringOrNull(SettingsKeys.JOKE_TYPE)
        return if (value != null) {
            JokeType.valueOf(value)
        } else {
            JokeType.ANY
        }
    }

    override suspend fun saveBlackList(list: List<JokeBlackListItem>) {
        val value = Json.encodeToString(
            ListSerializer(JokeBlackListItem.serializer()),
            list
        )
        settings.putString(SettingsKeys.JOKE_BLACKLIST, value)
    }

    override suspend fun getBlackList(): List<JokeBlackListItem> {
        val value = settings.getStringOrNull(SettingsKeys.JOKE_BLACKLIST) ?: return emptyList()
        return Json.decodeFromString<List<JokeBlackListItem>>(value)
    }
}
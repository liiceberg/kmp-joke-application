package com.itis.joke.core.data.datasource.local

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.data.settings.SettingsKeys
import com.russhwolf.settings.Settings
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class JokeSettingsDataSource(
    private val settings: Settings
) {
    fun saveCategory(category: JokeCategory) {
        settings.putString(SettingsKeys.JOKE_CATEGORY, category.name)
    }

    fun getCategory(): JokeCategory {
        val value = settings.getStringOrNull(SettingsKeys.JOKE_CATEGORY)
        return if (value != null) {
            JokeCategory.valueOf(value)
        } else {
            JokeCategory.ANY
        }
    }

    fun saveType(type: JokeType) {
        settings.putString(SettingsKeys.JOKE_TYPE, type.name)
    }

    fun getType(): JokeType {
        val value = settings.getStringOrNull(SettingsKeys.JOKE_TYPE)
        return if (value != null) {
            JokeType.valueOf(value)
        } else {
            JokeType.ANY
        }
    }

    fun saveBlackList(list: List<JokeBlackListItem>) {
        val value = Json.Default.encodeToString(
            ListSerializer(JokeBlackListItem.serializer()),
            list
        )
        settings.putString(SettingsKeys.JOKE_BLACKLIST, value)
    }

    fun getBlackList(): List<JokeBlackListItem> {
        val value = settings.getStringOrNull(SettingsKeys.JOKE_BLACKLIST) ?: return emptyList()
        return Json.Default.decodeFromString<List<JokeBlackListItem>>(value)
    }
}
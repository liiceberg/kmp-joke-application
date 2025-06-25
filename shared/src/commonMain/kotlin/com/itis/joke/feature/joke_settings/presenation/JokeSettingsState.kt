package com.itis.joke.feature.joke_settings.presenation

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.ui.viewmodel.model.UiState

data class JokeSettingsState(
    val category: JokeCategory = JokeCategory.ANY,
    val type: JokeType = JokeType.ANY,
    val blackList: List<JokeBlackListItem> = emptyList(),
) : UiState
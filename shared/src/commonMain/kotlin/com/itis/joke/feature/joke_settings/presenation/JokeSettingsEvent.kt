package com.itis.joke.feature.joke_settings.presenation

import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.common.model.JokeType
import com.itis.joke.core.ui.viewmodel.model.UiEvent

sealed interface JokeSettingsEvent : UiEvent {
    data object OnSave : JokeSettingsEvent
    data class OnCategoryChange(val category: JokeCategory) : JokeSettingsEvent
    data class OnJokeTypeChange(val jokeType: JokeType) : JokeSettingsEvent
    data class OnBlackListChange(val item: JokeBlackListItem, val checked: Boolean) : JokeSettingsEvent
}
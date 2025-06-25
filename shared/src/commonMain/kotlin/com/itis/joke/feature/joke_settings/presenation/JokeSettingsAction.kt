package com.itis.joke.feature.joke_settings.presenation

import com.itis.joke.core.ui.viewmodel.model.UiAction

sealed interface JokeSettingsAction : UiAction {
    data class ShowSaveToast(val isSuccess: Boolean): JokeSettingsAction
}
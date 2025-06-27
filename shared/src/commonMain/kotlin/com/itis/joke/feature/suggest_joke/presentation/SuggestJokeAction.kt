package com.itis.joke.feature.suggest_joke.presentation

import com.itis.joke.core.ui.viewmodel.model.UiAction

sealed interface SuggestJokeAction : UiAction {
    data class ShowSubmitToast(val isSuccess: Boolean): SuggestJokeAction
}
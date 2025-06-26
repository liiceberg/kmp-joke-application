package com.itis.joke.feature.random_joke.presentation

import com.itis.joke.core.ui.viewmodel.model.UiEvent

sealed interface RandomJokeEvent : UiEvent {
    data object OnGenerateClick : RandomJokeEvent
}
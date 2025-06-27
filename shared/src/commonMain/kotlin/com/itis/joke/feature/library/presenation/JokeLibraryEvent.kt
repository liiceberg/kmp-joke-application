package com.itis.joke.feature.library.presenation

import com.itis.joke.core.ui.viewmodel.model.UiEvent

sealed interface JokeLibraryEvent : UiEvent {
    data class OnSearchQueryChange(val query: String) : JokeLibraryEvent
}
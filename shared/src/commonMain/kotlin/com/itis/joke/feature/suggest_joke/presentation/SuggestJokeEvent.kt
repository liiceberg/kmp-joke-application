package com.itis.joke.feature.suggest_joke.presentation

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.ui.viewmodel.model.UiEvent

sealed interface SuggestJokeEvent : UiEvent {
    data class OnJokeFilled(val joke: String): SuggestJokeEvent
    data class OnJokeAdditionalPartFilled(val joke: String): SuggestJokeEvent
    data class OnCategoryChange(val category: JokeCategory) : SuggestJokeEvent
    data class OnBlackListChange(val item: JokeBlackListItem, val checked: Boolean) : SuggestJokeEvent
    data object OnSubmit : SuggestJokeEvent
}
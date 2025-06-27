package com.itis.joke.feature.suggest_joke.presentation

import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.model.UiState

data class SuggestJokeState(
    val joke: String = "",
    val jokeAdditionalPart: String = "",
    val category: JokeCategory = JokeCategory.ANY,
    val blackList: List<JokeBlackListItem> = emptyList(),
    val loadState: LoadState = LoadState.Initial,
) : UiState
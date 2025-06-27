package com.itis.joke.feature.random_joke.presentation

import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.model.UiState
import com.itis.joke.core.common.model.JokeModel

data class RandomJokeState(
    val joke: JokeModel? = null,
    val loadState: LoadState = LoadState.Initial,
) : UiState
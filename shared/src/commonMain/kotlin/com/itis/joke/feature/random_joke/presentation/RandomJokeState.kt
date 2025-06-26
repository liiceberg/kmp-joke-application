package com.itis.joke.feature.random_joke.presentation

import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.model.UiState
import com.itis.joke.feature.random_joke.domain.RandomJokeModel

data class RandomJokeState(
    val joke: RandomJokeModel? = null,
    val loadState: LoadState = LoadState.Initial,
) : UiState
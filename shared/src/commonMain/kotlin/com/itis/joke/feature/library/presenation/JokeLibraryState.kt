package com.itis.joke.feature.library.presenation

import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.model.UiState

data class JokeLibraryState(
    val query: String = "",
    val items: List<String> = emptyList(),
    val loadState: LoadState = LoadState.Initial,
) : UiState
package com.itis.joke.feature.library.presenation

import androidx.lifecycle.viewModelScope
import com.itis.joke.core.common.exceptions.ExceptionHandlerDelegate
import com.itis.joke.core.common.exceptions.runCatching
import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.BaseViewModel
import com.itis.joke.feature.library.domain.GetJokesUseCase
import kotlinx.coroutines.launch

class JokeLibraryViewModel(
    private val getJokesUseCase: GetJokesUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
) : BaseViewModel<JokeLibraryState, JokeLibraryEvent, JokeLibraryAction>(JokeLibraryState()) {

    private var allJokes: List<String> = emptyList()

    init {
//        loadJokes()
    }

    override fun obtainEvent(event: JokeLibraryEvent) {
        when (event) {
            is JokeLibraryEvent.OnSearchQueryChange -> {
                event.query.let {
                    viewState = viewState.copy(query = it)
                    filter(it.trim())
                }
            }
        }
    }

    private fun filter(query: String) {
        viewState = if (query.isBlank()) {
            viewState.copy(items = allJokes)
        } else {
            viewState.copy(
                items = allJokes.filter { it.lowercase().contains(query.lowercase()) }
            )
        }
    }

    private fun loadJokes() {
        viewModelScope.launch {
            runCatching(exceptionHandlerDelegate) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getJokesUseCase()
            }.onSuccess {
                allJokes = it.map { it.joke() }
                viewState = viewState.copy(items = allJokes, loadState = LoadState.Success)

            }.onFailure { ex ->
                ex.message?.let { viewState = viewState.copy(loadState = LoadState.Error(it)) }
            }
        }
    }
}
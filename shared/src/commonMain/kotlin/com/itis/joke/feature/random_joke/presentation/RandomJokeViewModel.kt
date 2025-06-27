package com.itis.joke.feature.random_joke.presentation

import androidx.lifecycle.viewModelScope
import com.itis.joke.core.common.exceptions.ExceptionHandlerDelegate
import com.itis.joke.core.common.exceptions.runCatching
import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.BaseViewModel
import com.itis.joke.feature.random_joke.domain.usecase.GetRandomJokeUseCase
import kotlinx.coroutines.launch

class RandomJokeViewModel(
    private val getRandomJokeUseCase: GetRandomJokeUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
) : BaseViewModel<RandomJokeState, RandomJokeEvent, RandomJokeAction>(RandomJokeState()) {

    init {
//        generateJoke()
    }

    override fun obtainEvent(event: RandomJokeEvent) {
        when (event) {
            RandomJokeEvent.OnGenerateClick -> generateJoke()
        }
    }

    private fun generateJoke() {
        viewModelScope.launch {
            runCatching(exceptionHandlerDelegate) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getRandomJokeUseCase()
            }.onSuccess {
                viewState = viewState.copy(joke = it, loadState = LoadState.Success)
            }.onFailure { ex ->
                ex.message?.let { viewState = viewState.copy(loadState = LoadState.Error(it)) }
            }
        }
    }
}
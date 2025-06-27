package com.itis.joke.feature.suggest_joke.presentation

import androidx.lifecycle.viewModelScope
import com.itis.joke.core.common.exceptions.ExceptionHandlerDelegate
import com.itis.joke.core.common.exceptions.runCatching
import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.BaseViewModel
import com.itis.joke.feature.suggest_joke.domain.SendJokeUseCase
import kotlinx.coroutines.launch

class SuggestJokeViewModel(
    private val sendJokeUseCase: SendJokeUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val mapper: SuggestJokeStateMapper,
) : BaseViewModel<SuggestJokeState, SuggestJokeEvent, SuggestJokeAction>(SuggestJokeState()) {

    override fun obtainEvent(event: SuggestJokeEvent) {
        when (event) {
            is SuggestJokeEvent.OnCategoryChange -> updateCategory(event.category)
            is SuggestJokeEvent.OnJokeFilled -> updateJoke(event.joke)
            is SuggestJokeEvent.OnJokeAdditionalPartFilled -> updateJokeAdditionalPart(event.joke)
            is SuggestJokeEvent.OnBlackListChange -> updateBlacklist(event.item, event.checked)
            SuggestJokeEvent.OnSubmit -> send()
        }
    }

    private fun updateJoke(joke: String) {
        viewState = viewState.copy(joke = joke)
    }

    private fun updateJokeAdditionalPart(joke: String) {
        viewState = viewState.copy(jokeAdditionalPart = joke)
    }

    private fun updateCategory(category: JokeCategory) {
        viewState = viewState.copy(category = category)
    }

    private fun updateBlacklist(item: JokeBlackListItem, checked: Boolean) {
        val newList = if (checked) {
            viewState.blackList + item
        } else {
            viewState.blackList - item
        }
        viewState = viewState.copy(blackList = newList)
    }

    private fun send() {
        viewModelScope.launch {
            runCatching(exceptionHandlerDelegate) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                sendJokeUseCase(
                    mapper.mapSuggestJokeStateToSubmitJokeModel(viewState)
                )
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewAction = SuggestJokeAction.ShowSubmitToast(true)
            }.onFailure { ex ->
                viewAction = SuggestJokeAction.ShowSubmitToast(false)
                viewState = viewState.copy(loadState = LoadState.Initial)
            }
        }
    }

}
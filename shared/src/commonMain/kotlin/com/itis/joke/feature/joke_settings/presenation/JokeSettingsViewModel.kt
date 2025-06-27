package com.itis.joke.feature.joke_settings.presenation

import androidx.lifecycle.viewModelScope
import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.common.model.JokeType
import com.itis.joke.core.ui.viewmodel.BaseViewModel
import com.itis.joke.feature.joke_settings.domain.usecase.GetBlacklistUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.GetJokeCategoryUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.GetJokeTypeUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.SetJokeSettingsUseCase
import kotlinx.coroutines.launch

class JokeSettingsViewModel(
    private val getJokeTypeUseCase: GetJokeTypeUseCase,
    private val getJokeCategoryUseCase: GetJokeCategoryUseCase,
    private val getBlacklistUseCase: GetBlacklistUseCase,
    private val setJokeSettingsUseCase: SetJokeSettingsUseCase,
) : BaseViewModel<JokeSettingsState, JokeSettingsEvent, JokeSettingsAction>(
    JokeSettingsState()
) {
    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val category = getJokeCategoryUseCase()
            val type = getJokeTypeUseCase()
            val blackList = getBlacklistUseCase()

            viewState = viewState.copy(
                category = category,
                type = type,
                blackList = blackList
            )
        }
    }

    override fun obtainEvent(event: JokeSettingsEvent) {
        when (event) {
            is JokeSettingsEvent.OnCategoryChange -> updateCategory(event.category)
            is JokeSettingsEvent.OnJokeTypeChange -> updateType(event.jokeType)
            is JokeSettingsEvent.OnBlackListChange -> updateBlacklist(event.item, event.checked)
            JokeSettingsEvent.OnSave -> saveSettings()
        }
    }

    private fun updateCategory(category: JokeCategory) {
        viewState = viewState.copy(category = category)
    }

    private fun updateType(type: JokeType) {
        viewState = viewState.copy(type = type)
    }

    private fun updateBlacklist(item: JokeBlackListItem, checked: Boolean) {
        val newList = if (checked) {
            viewState.blackList + item
        } else {
            viewState.blackList - item
        }
        viewState = viewState.copy(blackList = newList)
    }

    private fun saveSettings() {
        viewModelScope.launch {
            runCatching {
                setJokeSettingsUseCase(
                    category = viewState.category,
                    type = viewState.type,
                    blackList = viewState.blackList
                )
            }.onSuccess {
                viewAction = JokeSettingsAction.ShowSaveToast(true)
            }.onFailure {
                viewAction = JokeSettingsAction.ShowSaveToast(false)
            }
        }
    }
}
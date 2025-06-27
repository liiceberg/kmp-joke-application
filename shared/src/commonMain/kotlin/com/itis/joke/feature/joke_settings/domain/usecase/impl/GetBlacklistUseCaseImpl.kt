package com.itis.joke.feature.joke_settings.domain.usecase.impl

import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository
import com.itis.joke.feature.joke_settings.domain.usecase.GetBlacklistUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetBlacklistUseCaseImpl(
    private val repository: JokeSettingsRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetBlacklistUseCase {

    override suspend fun invoke(): List<JokeBlackListItem> {
        return withContext(dispatcher) {
            repository.getBlackList()
        }
    }

}
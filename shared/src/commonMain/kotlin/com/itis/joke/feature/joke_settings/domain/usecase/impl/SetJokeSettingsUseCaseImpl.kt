package com.itis.joke.feature.joke_settings.domain.usecase.impl

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository
import com.itis.joke.feature.joke_settings.domain.usecase.SetJokeSettingsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SetJokeSettingsUseCaseImpl(
    private val repository: JokeSettingsRepository,
    private val dispatcher: CoroutineDispatcher,
) : SetJokeSettingsUseCase {

    override suspend fun invoke(
        type: JokeType,
        category: JokeCategory,
        blackList: List<JokeBlackListItem>
    ) {
        withContext(dispatcher) {
            repository.saveType(type)
            repository.saveCategory(category)
            repository.saveBlackList(blackList)
        }
    }

}
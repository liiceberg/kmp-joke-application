package com.itis.joke.feature.joke_settings.domain.usecase.impl

import com.itis.joke.core.common.model.JokeType
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository
import com.itis.joke.feature.joke_settings.domain.usecase.GetJokeTypeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetJokeTypeUseCaseImpl(
    private val repository: JokeSettingsRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetJokeTypeUseCase {

    override suspend fun invoke(): JokeType {
        return withContext(dispatcher) {
            repository.getType()
        }
    }

}
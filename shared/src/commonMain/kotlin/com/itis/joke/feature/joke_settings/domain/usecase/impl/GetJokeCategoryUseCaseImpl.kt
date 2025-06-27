package com.itis.joke.feature.joke_settings.domain.usecase.impl

import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository
import com.itis.joke.feature.joke_settings.domain.usecase.GetJokeCategoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetJokeCategoryUseCaseImpl(
    private val repository: JokeSettingsRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetJokeCategoryUseCase{

    override suspend fun invoke(): JokeCategory {
        return withContext(dispatcher) {
            repository.getCategory()
        }
    }
}
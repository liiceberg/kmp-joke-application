package com.itis.joke.feature.random_joke.domain.usecase.impl

import com.itis.joke.core.data.datasource.remote.model.JokeModel
import com.itis.joke.feature.random_joke.domain.RandomJokeRepository
import com.itis.joke.feature.random_joke.domain.usecase.GetRandomJokeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetRandomJokeUseCaseImpl(
    private val repository: RandomJokeRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetRandomJokeUseCase {

    override suspend fun invoke(): JokeModel {
        return withContext(dispatcher) {
            repository.getJoke()
        }
    }
}
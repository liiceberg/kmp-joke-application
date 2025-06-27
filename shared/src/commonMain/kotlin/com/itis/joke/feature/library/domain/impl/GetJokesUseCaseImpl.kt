package com.itis.joke.feature.library.domain.impl

import com.itis.joke.feature.library.domain.GetJokesUseCase
import com.itis.joke.feature.library.domain.JokeLibraryRepository
import com.itis.joke.core.common.model.JokeModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetJokesUseCaseImpl(
    private val repository: JokeLibraryRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetJokesUseCase {
    override suspend fun invoke(): List<JokeModel> {
        return withContext(dispatcher) { repository.getAll() }
    }
}
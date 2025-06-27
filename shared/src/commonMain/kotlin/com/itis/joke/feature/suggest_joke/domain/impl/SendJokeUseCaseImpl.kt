package com.itis.joke.feature.suggest_joke.domain.impl

import com.itis.joke.feature.suggest_joke.domain.SendJokeUseCase
import com.itis.joke.feature.suggest_joke.domain.SubmitJokeModel
import com.itis.joke.feature.suggest_joke.domain.SuggestJokeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SendJokeUseCaseImpl(
    private val repository: SuggestJokeRepository,
    private val dispatcher: CoroutineDispatcher,
) : SendJokeUseCase {

    override suspend fun invoke(joke: SubmitJokeModel) {
        withContext(dispatcher) {
            repository.sendJoke(joke)
        }
    }

}
package com.itis.joke.feature.auth.domain.usecase.impl

import com.itis.joke.feature.auth.domain.repository.AuthRepository
import com.itis.joke.feature.auth.domain.usecase.RegisterUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class RegisterUseCaseImpl(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher,
) : RegisterUseCase {

    override suspend fun invoke(username: String, email: String, password: String) {
        withContext(dispatcher) {
            authRepository.register(username = username, email = email, password = password)
        }
    }

}
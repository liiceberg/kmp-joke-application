package com.itis.joke.feature.auth.domain.usecase.impl

import com.itis.joke.feature.auth.domain.repository.AuthRepository
import com.itis.joke.feature.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class LoginUseCaseImpl(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher,
) : LoginUseCase {

    override suspend fun invoke(
        email: String,
        password: String
    ) {
        withContext(dispatcher) {
            authRepository.login(email, password)
        }
    }

}
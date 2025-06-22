package com.itis.joke.feature.auth.domain.usecase

interface RegisterUseCase {
    suspend operator fun invoke(username: String, email: String, password: String)
}
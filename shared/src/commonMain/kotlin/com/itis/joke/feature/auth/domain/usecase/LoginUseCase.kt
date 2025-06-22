package com.itis.joke.feature.auth.domain.usecase

interface LoginUseCase {
    suspend operator fun invoke( email: String, password: String)
}
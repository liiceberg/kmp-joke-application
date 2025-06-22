package com.itis.joke.feature.auth.domain.repository

interface AuthRepository {
    suspend fun register(username: String, email: String, password: String)
    suspend fun login(email:String, password: String)
}
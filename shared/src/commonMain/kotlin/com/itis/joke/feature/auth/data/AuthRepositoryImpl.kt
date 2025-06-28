package com.itis.joke.feature.auth.data

import com.itis.joke.core.common.exceptions.AppException
import com.itis.joke.feature.auth.domain.repository.AuthRepository

internal class AuthRepositoryImpl(
    private val usersDataSource: UsersDataSource,
) : AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ) {
        if (usersDataSource.isUserExists(email)) {
            throw AppException.SuchEmailAlreadyRegistered("User with email $email already exist")
        } else {
            usersDataSource.createUser(username, email, password)
        }
    }

    override suspend fun login(email: String, password: String) {
        val u = usersDataSource.getUser(email, password) ?: throw AppException.InvalidCredentials("Email or password entered incorrectly")
        println(u)
    }

}
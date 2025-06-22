package com.itis.joke.feature.auth.data

import com.itis.joke.Database
import com.itis.joke.User

internal class UsersDataSource(
    private val database: Database,
) {
    fun createUser(username: String, email: String, password: String): Long? {
        return database.userQueries.run {
            insertUser(
                username = username,
                email = email,
                password = password
            )
            lastInsertedId().executeAsOne()
        }
    }

    fun getUser(email: String, password: String): User? {
        return database.userQueries
            .getByEmailAndPassword(email = email, password = password)
            .executeAsOneOrNull()
    }

    fun isUserExists(email: String): Boolean {
        return database.userQueries
            .getByEmail(email)
            .executeAsOneOrNull() != null
    }
}
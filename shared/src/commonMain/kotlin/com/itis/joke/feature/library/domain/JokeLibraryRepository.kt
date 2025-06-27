package com.itis.joke.feature.library.domain

import com.itis.joke.core.data.datasource.remote.model.JokeModel

interface JokeLibraryRepository {
    suspend fun getAll() : List<JokeModel>
}
package com.itis.joke.feature.library.domain

import com.itis.joke.core.common.model.JokeModel

interface JokeLibraryRepository {
    suspend fun getAll() : List<JokeModel>
}
package com.itis.joke.feature.library.domain

import com.itis.joke.core.common.model.JokeModel

interface GetJokesUseCase {
    suspend operator fun invoke() : List<JokeModel>
}
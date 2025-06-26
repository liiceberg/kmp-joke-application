package com.itis.joke.feature.random_joke.domain

import com.itis.joke.core.common.joke.JokeType

abstract class RandomJokeModel(
    val type: JokeType,
)

data class SingleJokeModel(
    val joke: String
) : RandomJokeModel(JokeType.SINGLE)

data class TwoPartJokeModel(
    val setup: String,
    val delivery: String
) : RandomJokeModel(JokeType.TWOPART)
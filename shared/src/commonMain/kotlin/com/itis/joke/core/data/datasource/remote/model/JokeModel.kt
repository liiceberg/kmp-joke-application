package com.itis.joke.core.data.datasource.remote.model

import com.itis.joke.core.common.joke.JokeType

abstract class JokeModel(
    val type: JokeType,
) {
    abstract fun joke() : String
}

data class SingleJokeModel(
    val joke: String
) : JokeModel(JokeType.SINGLE) {
    override fun joke(): String = joke
}

data class TwoPartJokeModel(
    val setup: String,
    val delivery: String
) : JokeModel(JokeType.TWOPART) {
    override fun joke(): String = "—$setup\n—$delivery"
}
package com.itis.joke.feature.random_joke.data

import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.data.datasource.remote.JokeResponse
import com.itis.joke.feature.random_joke.domain.RandomJokeModel
import com.itis.joke.feature.random_joke.domain.SingleJokeModel
import com.itis.joke.feature.random_joke.domain.TwoPartJokeModel

class RandomJokeMapper {
    fun mapResponseToModel(response: JokeResponse) : RandomJokeModel {
        return when(response.type) {
            JokeType.SINGLE.value -> SingleJokeModel(response.joke ?: "")
            JokeType.TWOPART.value -> TwoPartJokeModel(response.setup ?: "", response.delivery ?: "")
            else -> throw IllegalArgumentException()
        }
    }
}
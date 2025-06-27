package com.itis.joke.core.data.datasource.remote.model.mapper

import com.itis.joke.core.common.model.JokeType
import com.itis.joke.core.common.model.JokeModel
import com.itis.joke.core.data.datasource.remote.model.JokeResponse
import com.itis.joke.core.common.model.SingleJokeModel
import com.itis.joke.core.common.model.TwoPartJokeModel

class JokeModelMapper {
    fun mapResponseToModel(response: JokeResponse) : JokeModel {
        return when(response.type) {
            JokeType.SINGLE.value -> SingleJokeModel(response.joke ?: "")
            JokeType.TWOPART.value -> TwoPartJokeModel(
                response.setup ?: "",
                response.delivery ?: ""
            )
            else -> throw IllegalArgumentException()
        }
    }
}
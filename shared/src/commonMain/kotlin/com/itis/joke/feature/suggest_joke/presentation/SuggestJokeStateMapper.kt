package com.itis.joke.feature.suggest_joke.presentation

import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.data.datasource.remote.model.SingleJokeModel
import com.itis.joke.core.data.datasource.remote.model.TwoPartJokeModel
import com.itis.joke.feature.suggest_joke.domain.SubmitJokeModel

class SuggestJokeStateMapper {

    fun mapSuggestJokeStateToSubmitJokeModel(state: SuggestJokeState): SubmitJokeModel {
        val type = if (state.jokeAdditionalPart.isBlank()) JokeType.SINGLE else JokeType.TWOPART
        val joke = if (type == JokeType.SINGLE) {
            SingleJokeModel(state.joke)
        } else {
            TwoPartJokeModel(state.joke.trim(), state.jokeAdditionalPart.trim())
        }
        return SubmitJokeModel(
            joke = joke,
            category = state.category,
            blacklist = state.blackList,
        )
    }
}
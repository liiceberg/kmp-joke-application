package com.itis.joke.feature.suggest_joke.data

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.data.datasource.remote.model.JokeFlags
import com.itis.joke.core.data.datasource.remote.model.JokeSubmissionRequest
import com.itis.joke.core.data.datasource.remote.model.SingleJokeModel
import com.itis.joke.core.data.datasource.remote.model.TwoPartJokeModel
import com.itis.joke.feature.suggest_joke.domain.SubmitJokeModel

class SubmitJokeModelMapper {

    fun mapSubmitJokeModelToJokeSubmissionRequest(model: SubmitJokeModel): JokeSubmissionRequest {

        val flags = JokeFlags(
            nsfw = model.blacklist.contains(JokeBlackListItem.NSFW),
            religious = model.blacklist.contains(JokeBlackListItem.RELIGIOUS),
            political = model.blacklist.contains(JokeBlackListItem.POLITICAL),
            racist = model.blacklist.contains(JokeBlackListItem.RACIST),
            sexist = model.blacklist.contains(JokeBlackListItem.SEXIST),
            explicit = model.blacklist.contains(JokeBlackListItem.EXPLICIT),
        )
        var joke: String? = null
        var setup: String? = null
        var delivery: String? = null

        when(model.joke.type) {
            JokeType.SINGLE -> {
                joke = (model.joke as SingleJokeModel).joke
            }
            JokeType.TWOPART -> {
                model.joke as TwoPartJokeModel
                setup = model.joke.setup
                delivery = model.joke.delivery
            }
            else -> {}
        }

        return JokeSubmissionRequest(
            formatVersion = 3,
            category = model.category.value,
            type = model.joke.type.value,
            joke = joke,
            setup = setup,
            discovery = delivery,
            flags = flags,
            lang = "en"
        )
    }
}
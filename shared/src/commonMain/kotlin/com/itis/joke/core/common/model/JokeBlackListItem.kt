package com.itis.joke.core.common.model

import kotlinx.serialization.Serializable

@Serializable
enum class JokeBlackListItem(val value: String) {
    NSFW("nsfw"),
    RELIGIOUS("religious"),
    POLITICAL("political"),
    RACIST("racist"),
    SEXIST("sexist"),
    EXPLICIT("explicit")
}
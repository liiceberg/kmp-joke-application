package com.itis.joke.core.common.joke

import kotlinx.serialization.Serializable

@Serializable
enum class JokeBlackListItem {
    NSFW, RELIGIOUS, POLITICAL, RACIST, SEXIST, EXPLICIT
}
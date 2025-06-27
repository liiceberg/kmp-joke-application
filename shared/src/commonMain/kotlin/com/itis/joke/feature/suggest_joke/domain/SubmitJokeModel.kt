package com.itis.joke.feature.suggest_joke.domain

import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.common.model.JokeModel

data class SubmitJokeModel(
    val joke : JokeModel,
    val category: JokeCategory,
    val blacklist: List<JokeBlackListItem>,
)
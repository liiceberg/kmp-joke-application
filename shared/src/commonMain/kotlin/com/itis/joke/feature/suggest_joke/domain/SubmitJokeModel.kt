package com.itis.joke.feature.suggest_joke.domain

import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.data.datasource.remote.model.JokeModel

data class SubmitJokeModel(
    val joke : JokeModel,
    val category: JokeCategory,
    val blacklist: List<JokeBlackListItem>,
)
package com.itis.joke.feature.random_joke.data

import com.itis.joke.core.data.datasource.remote.JokeRemoteDataSource
import com.itis.joke.core.data.datasource.local.JokeSettingsDataSource
import com.itis.joke.core.data.datasource.remote.model.JokeModel
import com.itis.joke.core.data.datasource.remote.model.mapper.JokeModelMapper
import com.itis.joke.feature.random_joke.domain.RandomJokeRepository

class RandomJokeRepositoryImpl(
    private val settingsDataSource: JokeSettingsDataSource,
    private val jokeRemoteDataSource: JokeRemoteDataSource,
    private val mapper: JokeModelMapper,
) : RandomJokeRepository {

    override suspend fun getJoke(): JokeModel {
        val type = settingsDataSource.getType()
        val category = settingsDataSource.getCategory()
        val blacklist = settingsDataSource.getBlackList()
        return mapper.mapResponseToModel(
            jokeRemoteDataSource.getRandomJoke(
                category = category.value,
                type = type.value,
                blacklist = blacklist.map { it.value }
            )
        )
    }
}
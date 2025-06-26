package com.itis.joke.feature.random_joke.data

import com.itis.joke.core.data.datasource.remote.JokeRemoteDataSource
import com.itis.joke.core.data.datasource.local.JokeSettingsDataSource
import com.itis.joke.feature.random_joke.domain.RandomJokeModel
import com.itis.joke.feature.random_joke.domain.RandomJokeRepository

class RandomJokeRepositoryImpl(
    private val settingsDataSource: JokeSettingsDataSource,
    private val jokeRemoteDataSource: JokeRemoteDataSource,
    private val mapper: RandomJokeMapper,
) : RandomJokeRepository {

    override suspend fun getJoke(): RandomJokeModel {
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
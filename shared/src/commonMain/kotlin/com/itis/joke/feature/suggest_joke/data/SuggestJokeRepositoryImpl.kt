package com.itis.joke.feature.suggest_joke.data

import com.itis.joke.core.data.datasource.remote.JokeRemoteDataSource
import com.itis.joke.feature.suggest_joke.domain.SubmitJokeModel
import com.itis.joke.feature.suggest_joke.domain.SuggestJokeRepository

class SuggestJokeRepositoryImpl(
    private val datasource: JokeRemoteDataSource,
    private val mapper: SubmitJokeModelMapper,
) : SuggestJokeRepository {

    override suspend fun sendJoke(jokeModel: SubmitJokeModel) {
        datasource.submitJoke(
            mapper.mapSubmitJokeModelToJokeSubmissionRequest(jokeModel)
        )
    }

}
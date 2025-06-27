package com.itis.joke.feature.library.data

import com.itis.joke.core.data.datasource.remote.JokeRemoteDataSource
import com.itis.joke.feature.library.domain.JokeLibraryRepository
import com.itis.joke.core.data.datasource.remote.model.mapper.JokeModelMapper
import com.itis.joke.core.common.model.JokeModel

class JokeLibraryRepositoryImpl(
    private val dataSource: JokeRemoteDataSource,
    private val mapper: JokeModelMapper,
) : JokeLibraryRepository {

    override suspend fun getAll(): List<JokeModel> {
        return dataSource.getAll().map {  mapper.mapResponseToModel(it) }
    }

}
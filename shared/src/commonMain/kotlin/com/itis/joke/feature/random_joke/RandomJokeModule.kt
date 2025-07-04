package com.itis.joke.feature.random_joke

import com.itis.joke.core.data.datasource.remote.model.mapper.JokeModelMapper
import com.itis.joke.feature.random_joke.data.RandomJokeRepositoryImpl
import com.itis.joke.feature.random_joke.domain.RandomJokeRepository
import com.itis.joke.feature.random_joke.domain.usecase.GetRandomJokeUseCase
import com.itis.joke.feature.random_joke.domain.usecase.impl.GetRandomJokeUseCaseImpl
import com.itis.joke.feature.random_joke.presentation.RandomJokeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val randomJokeModule = module {
    factoryOf(::RandomJokeRepositoryImpl) { bind<RandomJokeRepository>() }
    factoryOf(::JokeModelMapper)

    factoryOf(::GetRandomJokeUseCaseImpl) { bind<GetRandomJokeUseCase>() }

    viewModelOf(::RandomJokeViewModel)
}
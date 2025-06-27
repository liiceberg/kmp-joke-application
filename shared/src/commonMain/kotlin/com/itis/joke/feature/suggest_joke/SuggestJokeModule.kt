package com.itis.joke.feature.suggest_joke

import com.itis.joke.feature.suggest_joke.data.SubmitJokeModelMapper
import com.itis.joke.feature.suggest_joke.data.SuggestJokeRepositoryImpl
import com.itis.joke.feature.suggest_joke.domain.SendJokeUseCase
import com.itis.joke.feature.suggest_joke.domain.SuggestJokeRepository
import com.itis.joke.feature.suggest_joke.domain.impl.SendJokeUseCaseImpl
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeStateMapper
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val suggestJokeModule = module {
    singleOf(::SuggestJokeRepositoryImpl) { bind<SuggestJokeRepository>() }
    singleOf(::SubmitJokeModelMapper)

    singleOf(::SendJokeUseCaseImpl) { bind<SendJokeUseCase>() }
    singleOf(::SuggestJokeStateMapper)

    viewModelOf(::SuggestJokeViewModel)
}
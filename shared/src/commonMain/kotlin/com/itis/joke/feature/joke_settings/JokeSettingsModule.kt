package com.itis.joke.feature.joke_settings

import com.itis.joke.feature.joke_settings.data.JokeSettingsRepositoryImpl
import com.itis.joke.feature.joke_settings.domain.JokeSettingsRepository
import com.itis.joke.feature.joke_settings.domain.usecase.GetBlacklistUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.GetJokeCategoryUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.GetJokeTypeUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.SetJokeSettingsUseCase
import com.itis.joke.feature.joke_settings.domain.usecase.impl.GetBlacklistUseCaseImpl
import com.itis.joke.feature.joke_settings.domain.usecase.impl.GetJokeCategoryUseCaseImpl
import com.itis.joke.feature.joke_settings.domain.usecase.impl.GetJokeTypeUseCaseImpl
import com.itis.joke.feature.joke_settings.domain.usecase.impl.SetJokeSettingsUseCaseImpl
import com.itis.joke.feature.joke_settings.presenation.JokeSettingsViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val jokeSettingsModule = module {
    singleOf(::JokeSettingsRepositoryImpl) { bind<JokeSettingsRepository>() }

    singleOf(::GetJokeTypeUseCaseImpl) { bind<GetJokeTypeUseCase>() }
    singleOf(::GetJokeCategoryUseCaseImpl) { bind<GetJokeCategoryUseCase>() }
    singleOf(::GetBlacklistUseCaseImpl) { bind<GetBlacklistUseCase>() }
    singleOf(::SetJokeSettingsUseCaseImpl) { bind<SetJokeSettingsUseCase>() }

    viewModelOf(::JokeSettingsViewModel)
}
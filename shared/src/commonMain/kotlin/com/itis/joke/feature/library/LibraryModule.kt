package com.itis.joke.feature.library

import com.itis.joke.feature.library.data.JokeLibraryRepositoryImpl
import com.itis.joke.feature.library.domain.GetJokesUseCase
import com.itis.joke.feature.library.domain.JokeLibraryRepository
import com.itis.joke.feature.library.domain.impl.GetJokesUseCaseImpl
import com.itis.joke.feature.library.presenation.JokeLibraryViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val libraryModule = module {
    singleOf(::JokeLibraryRepositoryImpl) { bind<JokeLibraryRepository>() }

    singleOf(::GetJokesUseCaseImpl) { bind<GetJokesUseCase>() }

    viewModelOf(::JokeLibraryViewModel)
}
package com.itis.joke.feature.auth

import com.itis.joke.feature.auth.data.AuthRepositoryImpl
import com.itis.joke.feature.auth.data.UsersDataSource
import com.itis.joke.feature.auth.domain.repository.AuthRepository
import com.itis.joke.feature.auth.domain.usecase.LoginUseCase
import com.itis.joke.feature.auth.domain.usecase.RegisterUseCase
import com.itis.joke.feature.auth.domain.usecase.impl.LoginUseCaseImpl
import com.itis.joke.feature.auth.domain.usecase.impl.RegisterUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<LoginUseCase> { LoginUseCaseImpl(get(), get()) }
    single<RegisterUseCase> { RegisterUseCaseImpl(get(), get()) }
    singleOf(::UsersDataSource)
}
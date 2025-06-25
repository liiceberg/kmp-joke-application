package com.itis.joke.feature.auth

import com.itis.joke.feature.auth.data.AuthRepositoryImpl
import com.itis.joke.feature.auth.data.UsersDataSource
import com.itis.joke.feature.auth.domain.repository.AuthRepository
import com.itis.joke.feature.auth.domain.usecase.LoginUseCase
import com.itis.joke.feature.auth.domain.usecase.RegisterUseCase
import com.itis.joke.feature.auth.domain.usecase.impl.LoginUseCaseImpl
import com.itis.joke.feature.auth.domain.usecase.impl.RegisterUseCaseImpl
import com.itis.joke.feature.auth.presentation.sign_in.SignInViewModel
import com.itis.joke.feature.auth.presentation.sign_up.SignUpViewModel
import com.itis.joke.feature.auth.presentation.util.UserDataValidator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::UsersDataSource)

    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    singleOf(::LoginUseCaseImpl) { bind<LoginUseCase>() }
    singleOf(::RegisterUseCaseImpl) { bind<RegisterUseCase>() }

    singleOf(::UserDataValidator)

    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
}
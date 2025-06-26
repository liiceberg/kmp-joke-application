package com.itis.joke.core.common

import com.itis.joke.core.common.config.Configuration
import com.itis.joke.core.common.config.PlatformConfiguration
import com.itis.joke.core.common.exceptions.ExceptionHandlerDelegate
import com.itis.joke.core.data.database.databaseModule
import com.itis.joke.core.data.datasource.remote.JokeRemoteDataSource
import com.itis.joke.core.data.datasource.local.JokeSettingsDataSource
import com.itis.joke.core.data.network.networkModule
import com.itis.joke.core.data.qualifier.qualifierModule
import com.itis.joke.core.data.settings.settingsModule
import com.itis.joke.feature.auth.authModule
import com.itis.joke.feature.joke_settings.jokeSettingsModule
import com.itis.joke.feature.library.libraryModule
import com.itis.joke.feature.random_joke.randomJokeModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

object CommonKmp {

    fun initKoin(
        configuration: Configuration,
        appDeclaration: KoinAppDeclaration = {},
    ) {
        startKoin {
            appDeclaration()
            modules(
                createConfiguration(configuration),
                createCommon(),
                networkModule,
                qualifierModule,
                settingsModule,
                databaseModule,

                authModule,
                libraryModule,
                jokeSettingsModule,
                randomJokeModule,
            )
        }
    }

    private fun createConfiguration(configuration: Configuration) = module {
        single<Configuration> { configuration }
        single<PlatformConfiguration> { configuration.platformConfiguration }
    }

    private fun createCommon() = module {
        single<CoroutineDispatcher> { Dispatchers.IO }
        singleOf(::ExceptionHandlerDelegate)

        singleOf(::JokeSettingsDataSource)
        singleOf(::JokeRemoteDataSource)
    }

}
package com.itis.joke.core.common

import com.itis.joke.core.common.config.Configuration
import com.itis.joke.core.common.config.PlatformConfiguration
import com.itis.joke.core.data.database.databaseModule
import com.itis.joke.core.data.network.networkModule
import com.itis.joke.core.data.qualifier.qualifierModule
import com.itis.joke.core.data.settings.settingsModule
import com.itis.joke.feature.auth.authModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
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
                networkModule,
                qualifierModule,
                settingsModule,
                databaseModule,

                authModule,
            )
        }
    }

    private fun createConfiguration(configuration: Configuration) = module {
        single<Configuration> { configuration }
        single<PlatformConfiguration> { configuration.platformConfiguration }
        single<CoroutineDispatcher> { Dispatchers.IO }
    }
}
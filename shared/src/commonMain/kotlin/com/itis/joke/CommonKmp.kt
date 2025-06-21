package com.itis.joke

import com.itis.joke.core.qualifier.qualifierModule
import com.itis.joke.core.config.Configuration
import com.itis.joke.core.config.PlatformConfiguration
import com.itis.joke.core.network.networkModule
import com.itis.joke.core.settings.storageModule
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
                storageModule,
            )
        }
    }

    private fun createConfiguration(configuration: Configuration) = module {
        single<Configuration> { configuration }
        single<PlatformConfiguration> { configuration.platformConfiguration }
    }
}

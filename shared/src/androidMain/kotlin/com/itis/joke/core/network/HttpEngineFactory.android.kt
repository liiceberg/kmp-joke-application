package com.itis.joke.core.network

import com.itis.joke.core.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.config
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

actual open class HttpEngineFactory actual constructor() {
    actual fun createEngine(config: Configuration): HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp.config {
        config {
            retryOnConnectionFailure(true)
        }
        if (config.isDebug) {
            addInterceptor(
                HttpLoggingInterceptor { Timber.tag("Network").d(it) }.apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                },
            )
        }
    }
}

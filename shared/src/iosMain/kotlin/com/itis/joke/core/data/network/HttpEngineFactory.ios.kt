package com.itis.joke.core.data.network

import com.itis.joke.core.common.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual open class HttpEngineFactory actual constructor() {

    actual fun createEngine(config: Configuration): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Darwin
    }
}
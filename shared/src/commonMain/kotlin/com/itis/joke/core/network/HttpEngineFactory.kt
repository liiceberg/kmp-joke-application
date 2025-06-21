package com.itis.joke.core.network

import com.itis.joke.core.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect open class HttpEngineFactory() {

    fun createEngine(config: Configuration): HttpClientEngineFactory<HttpClientEngineConfig>
}
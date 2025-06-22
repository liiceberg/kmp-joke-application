package com.itis.joke.core.data.network

import com.itis.joke.core.common.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect open class HttpEngineFactory() {

    fun createEngine(config: Configuration): HttpClientEngineFactory<HttpClientEngineConfig>
}
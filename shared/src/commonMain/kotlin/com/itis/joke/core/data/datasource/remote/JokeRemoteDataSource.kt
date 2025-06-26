package com.itis.joke.core.data.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.Json

class JokeRemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getRandomJoke(
        category: String,
        type: String?,
        blacklist: List<String>,
    ): JokeResponse {
        return httpClient.get("/joke/$category") {
            type?.let { parameter("type", type) }
            parameter("blacklistFlags", blacklist.joinToString(","))
        }.body<JokeResponse>()
    }

    suspend fun submitJoke(
        joke: String,
    ) {
        httpClient.post("/submit") {

        }.body<Json>()
    }
}
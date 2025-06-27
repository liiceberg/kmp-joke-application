package com.itis.joke.core.data.datasource.remote

import com.itis.joke.core.data.datasource.remote.model.JokeListResponse
import com.itis.joke.core.data.datasource.remote.model.JokeResponse
import com.itis.joke.core.data.datasource.remote.model.JokeSubmissionRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class JokeRemoteDataSource(
    private val httpClient: HttpClient,
) {

    private val allJokes = mutableListOf<JokeResponse>()

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

    suspend fun getAll() : List<JokeResponse> {
        if (allJokes.isEmpty()) {
            val pageSize = 10
            for (i in 0..100 step pageSize) {
                val resp = httpClient.get("/joke/Any") {
                    parameter("amount", pageSize)
                    parameter("idRange", "$i-${i + pageSize - 1}")
                }.body<JokeListResponse>()
                if (resp.error.not()) {
                    allJokes.addAll(resp.jokes)
                }
            }
        }
        return allJokes
    }

    suspend fun submitJoke(jokeSubmission: JokeSubmissionRequest) {
        httpClient.post("/submit") {
            contentType(ContentType.Application.Json)
            setBody(jokeSubmission)
        }.body<String>()
    }
}
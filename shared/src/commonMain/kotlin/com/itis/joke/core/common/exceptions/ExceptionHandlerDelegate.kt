package com.itis.joke.core.common.exceptions

import kotlinx.io.IOException

class ExceptionHandlerDelegate() {
    fun handleException(ex: Throwable): Throwable {
        println(ex.message)
        return when (ex) {
            is AppException -> ex
            is IOException -> AppException.UploadDataFailed("Please, check your internet connection and try again")
            else -> AppException.Unknown("We are working hard to fix the problem. Please, try again later")

        }
    }
}
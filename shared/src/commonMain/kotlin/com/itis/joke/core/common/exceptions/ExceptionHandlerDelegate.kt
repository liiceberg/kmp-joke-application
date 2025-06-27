package com.itis.joke.core.common.exceptions

import com.itis.joke.Res

class ExceptionHandlerDelegate() {
    fun handleException(ex: Throwable): Throwable {
        println(ex.message)
        return when (ex) {
            is AppException ->
                ex
            else -> {
                AppException.Unknown(Res.string.unknown_error)
            }
        }
    }
}
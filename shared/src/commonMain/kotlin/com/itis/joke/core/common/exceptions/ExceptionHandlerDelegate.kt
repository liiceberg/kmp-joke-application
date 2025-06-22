package com.itis.joke.core.common.exceptions

//import com.google.firebase.FirebaseNetworkException

class ExceptionHandlerDelegate() {
    fun handleException(ex: Throwable): Throwable {
        return when (ex) {
            is AppException ->
                ex
//            is FirebaseNetworkException -> {
//                AppException.UploadDataFailed("Please, check internet connection and try again")
//            }
            else -> {
                AppException.Unknown("We are working hard to fix the problem. Please, try again later")
            }
        }
    }
}
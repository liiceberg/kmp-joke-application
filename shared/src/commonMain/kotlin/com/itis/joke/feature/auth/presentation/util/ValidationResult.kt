package com.itis.joke.feature.auth.presentation.util

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
) {
    companion object {
        fun empty() = ValidationResult(true, null)
    }
}
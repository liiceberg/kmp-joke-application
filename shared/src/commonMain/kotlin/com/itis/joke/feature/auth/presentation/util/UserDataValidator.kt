package com.itis.joke.feature.auth.presentation.util
import kotlin.text.Regex

class UserDataValidator() {
    fun validateName(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(false, "Enter a username")
        }
        if (name.trim().matches(ALPHABETIC_CHARACTERS_WITH_WHITESPACE_REGEX).not()) {
            return ValidationResult(
                false,
                "Username must contain only latin alphabetic characters"
            )
        }
        return ValidationResult(true)
    }

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "Enter an email")
        }
        if (email.trim().matches(EMAIL_ADDRESS_REGEX).not()) {
            return ValidationResult(false, "Incorrect email")
        }
        return ValidationResult(true)
    }

    fun validatePassword(password: String): ValidationResult {
        with(password.trim()) {
            if (this.matches(EIGHT_SYMBOLS_AND_MORE_REGEX).not()) {
                return ValidationResult(
                    false,
                    "Password must be at least 8 characters long"
                )
            }
            if (this.matches(CONTAIN_UPPER_CASE_CHARACTER_REGEX).not()) {
                return ValidationResult(
                    false,
                    "Password must contain upper case character"
                )
            }
            if (this.matches(CONTAIN_LOWER_CASE_CHARACTER_REGEX).not()) {
                return ValidationResult(
                    false,
                    "Password must contain lower case character"
                )
            }
            if (this.matches(CONTAIN_DIGIT_REGEX).not()) {
                return ValidationResult(
                    false,
                    "Password must contain digit"
                )
            }
        }
        return ValidationResult(true)
    }

    fun validatePasswordNotBlank(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(false, "Enter a password")
        }
        return ValidationResult(true)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if (password.trim() != confirmPassword.trim()) {
            return ValidationResult(
                false,
                "Passwords not equals"
            )
        }
        return ValidationResult(true)
    }

    companion object {
        private val ALPHABETIC_CHARACTERS_WITH_WHITESPACE_REGEX = Regex("[A-Za-z ]+")
        private val EIGHT_SYMBOLS_AND_MORE_REGEX = Regex(".{8,}")
        private val CONTAIN_UPPER_CASE_CHARACTER_REGEX = Regex(".*[A-Z].*")
        private val CONTAIN_LOWER_CASE_CHARACTER_REGEX = Regex(".*[a-z].*")
        private val CONTAIN_DIGIT_REGEX = Regex(".*\\d.*")
        private val EMAIL_ADDRESS_REGEX = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    }
}
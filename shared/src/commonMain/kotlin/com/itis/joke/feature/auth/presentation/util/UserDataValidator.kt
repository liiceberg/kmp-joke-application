package com.itis.joke.feature.auth.presentation.util
import kotlin.text.Regex

class UserDataValidator() {
    fun validateName(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(false, EMPTY_USERNAME_ERROR)
        }
        if (name.trim().matches(ALPHABETIC_CHARACTERS_WITH_WHITESPACE_REGEX).not()) {
            return ValidationResult(false, INCORRECT_USERNAME_ERROR)
        }
        return ValidationResult(true)
    }

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, EMPTY_EMAIL_ERROR)
        }
        if (email.trim().matches(EMAIL_ADDRESS_REGEX).not()) {
            return ValidationResult(false, INCORRECT_EMAIL_ERROR)
        }
        return ValidationResult(true)
    }

    fun validatePassword(password: String): ValidationResult {
        with(password.trim()) {
            if (this.matches(EIGHT_SYMBOLS_AND_MORE_REGEX).not()) {
                return ValidationResult(
                    false,
                    PASSWORD_LENGTH_ERROR
                )
            }
            if (this.matches(CONTAIN_UPPER_CASE_CHARACTER_REGEX).not()) {
                return ValidationResult(
                    false,
                    PASSWORD_UPPER_CASE_CHAR_ERROR
                )
            }
            if (this.matches(CONTAIN_LOWER_CASE_CHARACTER_REGEX).not()) {
                return ValidationResult(
                    false,
                    PASSWORD_LOWER_CASE_CHAR_ERROR
                )
            }
            if (this.matches(CONTAIN_DIGIT_REGEX).not()) {
                return ValidationResult(false, PASSWORD_DIGIT_CHAR_ERROR)
            }
        }
        return ValidationResult(true)
    }

    fun validatePasswordNotBlank(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(false, EMPTY_PASSWORD_ERROR)
        }
        return ValidationResult(true)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if (password.trim() != confirmPassword.trim()) {
            return ValidationResult(false, PASSWORD_NOT_EQUALS_ERROR)
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

        private const val PASSWORD_NOT_EQUALS_ERROR = "Passwords not equals"
        private const val EMPTY_PASSWORD_ERROR = "Enter a password"
        private const val PASSWORD_DIGIT_CHAR_ERROR = "Password must contain digit"
        private const val PASSWORD_LOWER_CASE_CHAR_ERROR = "Password must contain lower case character"
        private const val PASSWORD_UPPER_CASE_CHAR_ERROR = "Password must contain upper case character"
        private const val PASSWORD_LENGTH_ERROR = "Password must be at least 8 characters long"
        private const val INCORRECT_EMAIL_ERROR = "Incorrect email"
        private const val EMPTY_EMAIL_ERROR = "Enter an email"
        private const val INCORRECT_USERNAME_ERROR = "Username must contain only latin alphabetic characters"
        private const val EMPTY_USERNAME_ERROR = "Enter a username"
    }
}
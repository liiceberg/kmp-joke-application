package com.itis.joke.feature.auth.presentation.util
import com.itis.joke.Res
import kotlin.text.Regex

class UserDataValidator() {
    fun validateName(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(false,Res.string.empty_username_error)
        }
        if (name.trim().matches(ALPHABETIC_CHARACTERS_WITH_WHITESPACE_REGEX).not()) {
            return ValidationResult(
                false,
                Res.string.incorrect_username_error
            )
        }
        return ValidationResult(true)
    }

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, Res.string.empty_email_error)
        }
        if (email.trim().matches(EMAIL_ADDRESS_REGEX).not()) {
            return ValidationResult(false, Res.string.email_error)
        }
        return ValidationResult(true)
    }

    fun validatePassword(password: String): ValidationResult {
        with(password.trim()) {
            if (this.matches(EIGHT_SYMBOLS_AND_MORE_REGEX).not()) {
                return ValidationResult(
                    false,
                    Res.string.password_length_error
                )
            }
            if (this.matches(CONTAIN_UPPER_CASE_CHARACTER_REGEX).not()) {
                return ValidationResult(
                    false,
                    Res.string.password_upper_case_char_error
                )
            }
            if (this.matches(CONTAIN_LOWER_CASE_CHARACTER_REGEX).not()) {
                return ValidationResult(
                    false,
                    Res.string.password_lower_case_char_error
                )
            }
            if (this.matches(CONTAIN_DIGIT_REGEX).not()) {
                return ValidationResult(
                    false,
                    Res.string.password_digit_char_error
                )
            }
        }
        return ValidationResult(true)
    }

    fun validatePasswordNotBlank(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(false, Res.string.empty_password_error)
        }
        return ValidationResult(true)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if (password.trim() != confirmPassword.trim()) {
            return ValidationResult(
                false,
                Res.string.passwords_not_equals_error
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
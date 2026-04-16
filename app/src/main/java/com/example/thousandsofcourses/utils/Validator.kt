package com.example.thousandsofcourses.utils

object Validator {

    fun validateEmail(email: String): String? {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return when {
            email.isBlank() -> "Email не может быть пустым"
            !regex.matches(email) -> "Неверный формат email"
            else -> null
        }
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Пароль не может быть пустым"
            password.length < 6 -> "Пароль должен быть не менее 6 символов"
            else -> null
        }
    }
}
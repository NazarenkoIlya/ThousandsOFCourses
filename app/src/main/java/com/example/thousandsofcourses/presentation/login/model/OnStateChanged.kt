package com.example.thousandsofcourses.presentation.login.model

sealed interface OnStateChanged
data class OnPasswordChanged(val text: String) : OnStateChanged
data class OnEmailChanged(val text: String) : OnStateChanged
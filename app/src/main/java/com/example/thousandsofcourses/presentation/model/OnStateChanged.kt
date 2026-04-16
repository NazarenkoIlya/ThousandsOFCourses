package com.example.thousandsofcourses.presentation.model

import kotlinx.coroutines.flow.Flow

sealed interface OnStateChanged
data class OnPasswordChanged(val text: String) : OnStateChanged
data class OnEmailChanged(val text: String) : OnStateChanged
package com.example.thousandsofcourses.presentation.favoritescreen.model

sealed interface OnStateChanged
data class OnLikeClicked(val courseId: String) : OnStateChanged
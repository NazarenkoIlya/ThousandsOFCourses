package com.example.thousandsofcourses.presentation.mainscreen.model

sealed interface OnStateChanged
data class OnLikeClicked(val courseId: String) : OnStateChanged
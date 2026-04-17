package com.example.thousandsofcourses.presentation.mainscreen.model

import com.example.thousandsofcourses.presentation.login.model.TitleItem

sealed interface ViewItem {
    val id: String
}

data class CoursesItem(
    override val id: String,
    val title: String,
    val text: String,
    val price: String,
    val startDate: String,
    val publishDate: String,
    val hasLike: Boolean = false,
): ViewItem

data class CoursesUIState(
    val uiState: UIState = UIState.Loading,
    val courses: List<CoursesItem> = emptyList()
)

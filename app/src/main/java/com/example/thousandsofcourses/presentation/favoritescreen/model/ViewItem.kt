package com.example.thousandsofcourses.presentation.favoritescreen.model

sealed interface ViewItem {
    val id: String
}

data class FavoriteCoursesItem(
    override val id: String,
    val title: String,
    val text: String,
    val price: String,
    val startDate: String,
    val publishDate: String,
    val hasLike: Boolean = false,
): ViewItem

data class FavoriteCoursesUIState(
    val uiState: UIState = UIState.Loading,
    val courses: List<FavoriteCoursesItem> = emptyList()
)
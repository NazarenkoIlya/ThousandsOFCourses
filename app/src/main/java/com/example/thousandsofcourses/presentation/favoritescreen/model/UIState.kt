package com.example.thousandsofcourses.presentation.favoritescreen.model

sealed class UIState {
    object Loading : UIState();
    object Success : UIState();
    data class Error(val message: String) : UIState();
}
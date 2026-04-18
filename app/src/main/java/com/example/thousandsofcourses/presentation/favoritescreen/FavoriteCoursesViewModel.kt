package com.example.thousandsofcourses.presentation.favoritescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandsofcourses.R
import com.example.thousandsofcourses.domain.model.Course
import com.example.thousandsofcourses.domain.usecase.DeleteFavoriteCoursesUseCase
import com.example.thousandsofcourses.domain.usecase.GetFavoriteCoursesUseCase
import com.example.thousandsofcourses.manager.ResourcesManager
import com.example.thousandsofcourses.presentation.favoritescreen.model.FavoriteCoursesItem
import com.example.thousandsofcourses.presentation.favoritescreen.model.FavoriteCoursesUIState
import com.example.thousandsofcourses.presentation.favoritescreen.model.OnLikeClicked
import com.example.thousandsofcourses.presentation.favoritescreen.model.OnStateChanged
import com.example.thousandsofcourses.presentation.favoritescreen.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteCoursesViewModel(
    private val getCoursesUseCase: GetFavoriteCoursesUseCase,
    private val resourcesManager: ResourcesManager,
    private val deleteFavoriteCoursesUseCase: DeleteFavoriteCoursesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteCoursesUIState())
    val state: StateFlow<FavoriteCoursesUIState> = _state

//    init {
//        loadData()
//    }

    fun loadData() {
        viewModelScope.launch {
            _state.value = FavoriteCoursesUIState(uiState = UIState.Loading)

            val courses = getCoursesUseCase.invoke().fold(
                onSuccess = {
                    val originalCourses = it.courses.map { it.toMapCoursesItem() }
                    FavoriteCoursesUIState(
                        courses = originalCourses,
                        uiState = UIState.Success
                    )
                },
                onFailure = {
                    FavoriteCoursesUIState(
                        courses = emptyList(),
                        uiState = UIState.Error(it.message.toString())
                    )
                }
            )
            _state.value = courses
        }
    }


    fun onEvent(onStateChanged: OnStateChanged) {
        when (onStateChanged) {
            is OnLikeClicked -> {
                viewModelScope.launch {
                    val id = onStateChanged.courseId
                    _state.update {
                        it.copy(
                            courses = it.courses.filter {
                                it.id != id
                            }
                        )
                    }
                    deleteFavoriteCoursesUseCase.invoke(id)
                }
            }
        }
    }

    private fun Course.toMapCoursesItem(): FavoriteCoursesItem {
        return FavoriteCoursesItem(
            id = id,
            title = title,
            text = text,
            price = resourcesManager.getString(R.string.price, price),
            startDate = startDate,
            publishDate = publishDate,
            hasLike = hasLike
        )
    }
}
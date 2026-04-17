package com.example.thousandsofcourses.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandsofcourses.R
import com.example.thousandsofcourses.domain.model.Course
import com.example.thousandsofcourses.domain.usecase.GetCoursesUseCase
import com.example.thousandsofcourses.manager.ResourcesManager
import com.example.thousandsofcourses.presentation.mainscreen.model.CoursesItem
import com.example.thousandsofcourses.presentation.mainscreen.model.CoursesUIState
import com.example.thousandsofcourses.presentation.mainscreen.model.OnLikeClicked
import com.example.thousandsofcourses.presentation.mainscreen.model.OnStateChanged
import com.example.thousandsofcourses.presentation.mainscreen.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val resourcesManager: ResourcesManager
) : ViewModel() {

    private val _state = MutableStateFlow(CoursesUIState())
    val state: StateFlow<CoursesUIState> = _state

    private var originalCourses: List<CoursesItem> = emptyList()
    private var isSorting = false

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = CoursesUIState(uiState = UIState.Loading)

            val courses = getCoursesUseCase.invoke().fold(
                onSuccess = {
                    originalCourses = it.courses.map { it.toMap() }
                    CoursesUIState(
                        courses = originalCourses,
                        uiState = UIState.Success
                    )
                },
                onFailure = {
                    CoursesUIState(
                        courses = emptyList(),
                        uiState = UIState.Error(it.message.toString())
                    )
                }
            )
            _state.value = courses
            isSorting = false
        }
    }

    fun sortingCourse() {
        val currentState = _state.value

        if (!isSorting) {
            val sortedCourses = currentState.courses.sortedByDescending { it.publishDate }
            _state.update { it.copy(courses = sortedCourses) }
            isSorting = true
        } else {
            _state.update { it.copy(courses = originalCourses) }
            isSorting = false
        }
    }


    fun onEvent(onStateChanged: OnStateChanged) {
        when (onStateChanged) {
            is OnLikeClicked -> {
                val id = onStateChanged.courseId
                _state.update {
                    it.copy(
                        courses = it.courses.map {
                            if (it.id == id) it.copy(hasLike = !it.hasLike)
                            else it
                        }
                    )
                }
            }
        }
    }

    private fun Course.toMap(): CoursesItem {
        return CoursesItem(
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
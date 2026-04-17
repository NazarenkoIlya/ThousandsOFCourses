package com.example.thousandsofcourses.domain.usecase

import com.example.thousandsofcourses.domain.model.CourseList
import com.example.thousandsofcourses.domain.repository.CourseRepository
import com.example.thousandsofcourses.domain.repository.FavoriteCoursesRepository

class GetFavoriteCoursesUseCase (
    private val favoriteCoursesRepository: FavoriteCoursesRepository
) {
    suspend operator fun invoke(): Result<CourseList> {
        return  try {
            val courses = favoriteCoursesRepository.getCourses()
            Result.success(courses)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}
package com.example.thousandsofcourses.domain.usecase

import com.example.thousandsofcourses.domain.model.Course
import com.example.thousandsofcourses.domain.repository.FavoriteCoursesRepository

class InsertFavoriteCoursesUseCase(
    private val favoriteCoursesRepository: FavoriteCoursesRepository
) {
    suspend operator fun invoke(course: Course): Result<Unit> {
        return try {
            favoriteCoursesRepository.insertCourse(course)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
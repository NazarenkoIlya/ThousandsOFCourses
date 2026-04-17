package com.example.thousandsofcourses.domain.usecase

import com.example.thousandsofcourses.domain.model.CourseList
import com.example.thousandsofcourses.domain.repository.CourseRepository
import com.example.thousandsofcourses.domain.repository.FavoriteCoursesRepository

class DeleteFavoriteCoursesUseCase (
    private val favoriteCoursesRepository: FavoriteCoursesRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return  try {
            favoriteCoursesRepository.deleteCourse(id)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}
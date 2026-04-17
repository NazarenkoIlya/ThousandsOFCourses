package com.example.thousandsofcourses.domain.usecase

import com.example.thousandsofcourses.domain.model.CourseList
import com.example.thousandsofcourses.domain.repository.CourseRepository

class GetCoursesUseCase(
   private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(): Result<CourseList> {
        return  try {
            val courses = courseRepository.getCourses()
            Result.success(courses)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}
package com.example.thousandsofcourses.domain.repository

import com.example.thousandsofcourses.domain.model.Course
import com.example.thousandsofcourses.domain.model.CourseList

interface FavoriteCoursesRepository {
    suspend fun insertCourse(course: Course)
    suspend fun getCourses(): CourseList
    suspend fun deleteCourse(id: String)

}
package com.example.thousandsofcourses.data.repository

import com.example.thousandsofcourses.data.mapper.toCourseList
import com.example.thousandsofcourses.data.service.CoursesService
import com.example.thousandsofcourses.domain.model.CourseList
import com.example.thousandsofcourses.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val coursesService: CoursesService
): CourseRepository {
    override suspend fun getCourses(): CourseList {
        return coursesService.getCourses().toCourseList()
    }
}
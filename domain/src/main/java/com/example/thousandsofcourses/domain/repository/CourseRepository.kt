package com.example.thousandsofcourses.domain.repository

import com.example.thousandsofcourses.domain.model.CourseList

interface CourseRepository {
    suspend fun getCourses(): CourseList
}
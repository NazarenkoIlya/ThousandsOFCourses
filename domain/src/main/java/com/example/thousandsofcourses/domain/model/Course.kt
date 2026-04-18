package com.example.thousandsofcourses.domain.model


data class CourseList(
    val courses: List<Course> = emptyList()
)

data class Course(
    val id: String,
    val title: String,
    val text: String,
    val price: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

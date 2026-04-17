package com.example.thousandsofcourses.data.model

import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    val courses: List<CourseDto>
)

data class CourseDto(
    val id: String,
    val title: String,
    val text: String,
    val price: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("hasLike")
    val hasLike: Boolean,
    @SerializedName("publishDate")
    val publishDate: String
)


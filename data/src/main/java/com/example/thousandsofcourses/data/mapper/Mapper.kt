package com.example.thousandsofcourses.data.mapper

import com.example.thousandsofcourses.data.model.CourseDto
import com.example.thousandsofcourses.data.model.CoursesResponse
import com.example.thousandsofcourses.data.model.room.dbo.FavoritesCoursesDbo
import com.example.thousandsofcourses.domain.model.Course
import com.example.thousandsofcourses.domain.model.CourseList

fun CoursesResponse.toCourseList(): CourseList {
    return CourseList(
        courses = courses.map { it ->
            Course(
                id = it.id,
                title = it.title,
                text = it.text,
                price = it.price,
                startDate = it.startDate,
                hasLike = it.hasLike,
                publishDate = it.publishDate
            )
        }
    )
}

fun FavoritesCoursesDbo.toCourse(): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun Course.toFavoritesCoursesDbo(): FavoritesCoursesDbo {
    return FavoritesCoursesDbo(
        id = id,
        title = title,
        text = text,
        price = price,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun CourseDto.toFavoritesCoursesDbo(): FavoritesCoursesDbo {
    return FavoritesCoursesDbo(
        id = id,
        title = title,
        text = text,
        price = price,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}
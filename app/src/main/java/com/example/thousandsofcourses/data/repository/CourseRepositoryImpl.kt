package com.example.thousandsofcourses.data.repository

import com.example.thousandsofcourses.data.mapper.toCourseList
import com.example.thousandsofcourses.data.mapper.toFavoritesCoursesDbo
import com.example.thousandsofcourses.data.model.room.dao.FavoritesCoursesDao
import com.example.thousandsofcourses.data.service.CoursesService
import com.example.thousandsofcourses.domain.model.CourseList
import com.example.thousandsofcourses.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val coursesService: CoursesService,
    private val favoritesCoursesDao: FavoritesCoursesDao
) : CourseRepository {
    override suspend fun getCourses(): CourseList {
        val courses = coursesService.getCourses()
        courses.courses.forEach {
            if (it.hasLike) favoritesCoursesDao.insertCourse(it.toFavoritesCoursesDbo())
        }
        return courses.toCourseList()
    }
}
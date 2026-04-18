package com.example.thousandsofcourses.data.repository

import com.example.thousandsofcourses.data.mapper.toCourse
import com.example.thousandsofcourses.data.mapper.toFavoritesCoursesDbo
import com.example.thousandsofcourses.data.model.room.dao.FavoritesCoursesDao
import com.example.thousandsofcourses.domain.model.Course
import com.example.thousandsofcourses.domain.model.CourseList
import com.example.thousandsofcourses.domain.repository.FavoriteCoursesRepository

class FavoriteCoursesRepositoryImpl(
    private val favoritesCoursesDao: FavoritesCoursesDao
) : FavoriteCoursesRepository {
    override suspend fun insertCourse(course: Course) {
        favoritesCoursesDao.insertCourse(course.toFavoritesCoursesDbo())
    }

    override suspend fun getCourses(): CourseList {
        val courses = favoritesCoursesDao.getAllCourses()
        return CourseList(courses = courses.map { it.toCourse() })
    }

    override suspend fun deleteCourse(id: String) {
        favoritesCoursesDao.deleteCourseById(id)
    }
}
package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.data.repository.CourseRepositoryImpl
import com.example.thousandsofcourses.data.repository.FavoriteCoursesRepositoryImpl
import com.example.thousandsofcourses.domain.repository.CourseRepository
import com.example.thousandsofcourses.domain.repository.FavoriteCoursesRepository
import org.koin.dsl.module

val dataModule = module {
    single<CourseRepository> {
        CourseRepositoryImpl(
            coursesService = get(),
            favoritesCoursesDao = get()
        )
    }
    single<FavoriteCoursesRepository> {
        FavoriteCoursesRepositoryImpl(
            favoritesCoursesDao = get()
        )
    }
}
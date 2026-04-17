package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.data.repository.CourseRepositoryImpl
import com.example.thousandsofcourses.domain.repository.CourseRepository
import org.koin.dsl.module

val dataModule = module {
    single<CourseRepository> {
        CourseRepositoryImpl(
            coursesService = get()
        )
    }
}
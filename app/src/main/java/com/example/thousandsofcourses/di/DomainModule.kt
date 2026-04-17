package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.domain.usecase.GetCoursesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCoursesUseCase(
            courseRepository = get()
        )
    }
}
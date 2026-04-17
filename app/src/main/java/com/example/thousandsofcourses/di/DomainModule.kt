package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.domain.usecase.DeleteFavoriteCoursesUseCase
import com.example.thousandsofcourses.domain.usecase.GetCoursesUseCase
import com.example.thousandsofcourses.domain.usecase.GetFavoriteCoursesUseCase
import com.example.thousandsofcourses.domain.usecase.InsertFavoriteCoursesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCoursesUseCase(
            courseRepository = get()
        )
    }

    factory {
        DeleteFavoriteCoursesUseCase(
            favoriteCoursesRepository = get()
        )
    }

    factory {
        GetFavoriteCoursesUseCase(
            favoriteCoursesRepository = get()
        )
    }

    factory {
        InsertFavoriteCoursesUseCase(
            favoriteCoursesRepository = get()
        )
    }
}
package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.presentation.login.LoginViewModel
import com.example.thousandsofcourses.presentation.mainscreen.CoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(
            resourcesManager = get()
        )
    }
    viewModel {
        CoursesViewModel(
            getCoursesUseCase = get(),
            resourcesManager = get()
        )
    }
}
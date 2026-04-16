package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(
            resourcesManager = get()
        )
    }
}
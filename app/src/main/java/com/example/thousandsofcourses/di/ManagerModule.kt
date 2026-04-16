package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.manager.ResourcesManager
import org.koin.dsl.module

val managerModule = module {
    single { ResourcesManager(get()) }
}
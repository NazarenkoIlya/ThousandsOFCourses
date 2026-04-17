package com.example.thousandsofcourses.di

import android.content.Context
import androidx.room.Room
import com.example.thousandsofcourses.data.model.room.FavoriteCoursesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataBaseModule = module {
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(
            context,
            FavoriteCoursesDatabase::class.java,
            "favorite_courses.db"
        ).fallbackToDestructiveMigration()
            .build()

    fun provideFavoriteCoursesDao(db: FavoriteCoursesDatabase) = db.favoritesCoursesDao()

    single { provideDataBase(androidContext()) }
    single { provideFavoriteCoursesDao(get()) }
}
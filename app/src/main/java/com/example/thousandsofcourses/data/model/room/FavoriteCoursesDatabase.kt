package com.example.thousandsofcourses.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thousandsofcourses.data.model.room.dao.FavoritesCoursesDao
import com.example.thousandsofcourses.data.model.room.dbo.FavoritesCoursesDbo

@Database(
    entities = [FavoritesCoursesDbo::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteCoursesDatabase : RoomDatabase() {
    abstract fun favoritesCoursesDao(): FavoritesCoursesDao
}
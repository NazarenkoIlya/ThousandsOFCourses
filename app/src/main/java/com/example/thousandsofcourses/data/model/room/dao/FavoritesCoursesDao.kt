package com.example.thousandsofcourses.data.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thousandsofcourses.data.model.room.dbo.FavoritesCoursesDbo


@Dao
interface FavoritesCoursesDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: FavoritesCoursesDbo)

    @Query("SELECT * FROM favorite_courses")
    suspend fun getAllCourses(): List<FavoritesCoursesDbo>

    @Query("DELETE FROM favorite_courses WHERE id = :id")
    suspend fun deleteCourseById(id: String)

}
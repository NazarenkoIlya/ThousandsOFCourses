package com.example.thousandsofcourses.data.model.room.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_courses")
data class FavoritesCoursesDbo (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val text: String,
    val price: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
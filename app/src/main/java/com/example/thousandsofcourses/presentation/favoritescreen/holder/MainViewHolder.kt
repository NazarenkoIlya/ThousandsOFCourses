package com.example.thousandsofcourses.presentation.favoritescreen.holder

import com.example.thousandsofcourses.presentation.favoritescreen.model.ViewItem

interface MainViewHolder {
    fun bind(item: ViewItem)
    open fun bind(item: ViewItem, payload: List<Any>) {}
}
package com.example.thousandsofcourses.presentation.holders

import com.example.thousandsofcourses.presentation.model.ViewItem

interface MainViewHolder {
    fun bind(item: ViewItem)
    open fun bind(item: ViewItem, payload: List<Any>) {}
}
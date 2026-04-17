package com.example.thousandsofcourses.presentation.mainscreen.holders

import com.example.thousandsofcourses.presentation.mainscreen.model.ViewItem


interface MainViewHolder {
    fun bind(item: ViewItem)
    open fun bind(item: ViewItem, payload: List<Any>) {}
}
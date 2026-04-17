package com.example.thousandsofcourses.presentation.login.holders

import com.example.thousandsofcourses.presentation.login.model.ViewItem

interface MainViewHolder {
    fun bind(item: ViewItem)
    open fun bind(item: ViewItem, payload: List<Any>) {}
}
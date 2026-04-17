package com.example.thousandsofcourses.presentation.login.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.LineViewLayoutBinding
import com.example.thousandsofcourses.presentation.login.model.ViewItem

class LineViewHolder (
    private val binding: LineViewLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {}
}
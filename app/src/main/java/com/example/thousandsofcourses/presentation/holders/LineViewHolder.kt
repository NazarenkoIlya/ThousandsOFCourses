package com.example.thousandsofcourses.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.LineViewLayoutBinding
import com.example.thousandsofcourses.databinding.TextBtnsLayoutBinding
import com.example.thousandsofcourses.presentation.model.ViewItem

class LineViewHolder (
    private val binding: LineViewLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {}
}
package com.example.thousandsofcourses.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.TextBtnsLayoutBinding
import com.example.thousandsofcourses.presentation.model.ViewItem

class TextButtonsItemHolder (
    private val binding: TextBtnsLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {}
}
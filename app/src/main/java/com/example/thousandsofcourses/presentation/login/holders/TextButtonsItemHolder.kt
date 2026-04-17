package com.example.thousandsofcourses.presentation.login.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.TextBtnsLayoutBinding
import com.example.thousandsofcourses.presentation.login.model.ViewItem

class TextButtonsItemHolder (
    private val binding: TextBtnsLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {}
}
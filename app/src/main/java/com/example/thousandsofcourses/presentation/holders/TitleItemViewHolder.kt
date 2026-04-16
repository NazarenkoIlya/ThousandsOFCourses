package com.example.thousandsofcourses.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.TitleLayoutBinding
import com.example.thousandsofcourses.presentation.model.TitleItem
import com.example.thousandsofcourses.presentation.model.ViewItem

class TitleItemViewHolder(
    private val binding: TitleLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        binding.headlineTextView.text = (item as TitleItem).text
    }
}
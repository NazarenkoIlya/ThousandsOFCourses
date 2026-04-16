package com.example.thousandsofcourses.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.TitleMediumLayoutBinding
import com.example.thousandsofcourses.presentation.model.TitleMediumItem
import com.example.thousandsofcourses.presentation.model.ViewItem

class TitleMediumItemHolder(
    private val binding: TitleMediumLayoutBinding
) : RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        binding.headlineTextView.text = (item as TitleMediumItem).text
    }
}
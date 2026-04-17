package com.example.thousandsofcourses.presentation.login.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.TitleMediumLayoutBinding
import com.example.thousandsofcourses.presentation.login.model.TitleMediumItem
import com.example.thousandsofcourses.presentation.login.model.ViewItem

class TitleMediumItemHolder(
    private val binding: TitleMediumLayoutBinding
) : RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        binding.headlineTextView.text = (item as TitleMediumItem).text
    }
}
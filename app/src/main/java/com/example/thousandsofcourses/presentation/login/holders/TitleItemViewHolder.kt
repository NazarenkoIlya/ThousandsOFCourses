package com.example.thousandsofcourses.presentation.login.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.TitleLayoutBinding
import com.example.thousandsofcourses.presentation.login.model.TitleItem
import com.example.thousandsofcourses.presentation.login.model.ViewItem

class TitleItemViewHolder(
    private val binding: TitleLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        binding.headlineTextView.text = (item as TitleItem).text
    }
}
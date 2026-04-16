package com.example.thousandsofcourses.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.ButtonLayoutBinding
import com.example.thousandsofcourses.presentation.model.ButtonItem
import com.example.thousandsofcourses.presentation.model.ViewItem

class ButtonItemHolder (
    private val binding:  ButtonLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        item as ButtonItem

        binding.button.isEnabled = item.isEnable
        binding.button.setOnClickListener {

        }
    }
}
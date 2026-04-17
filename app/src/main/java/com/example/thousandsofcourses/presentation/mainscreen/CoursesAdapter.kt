package com.example.thousandsofcourses.presentation.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.R
import com.example.thousandsofcourses.databinding.CourseItemLayoutBinding
import com.example.thousandsofcourses.presentation.mainscreen.holders.CoursesItemViewHolder
import com.example.thousandsofcourses.presentation.mainscreen.holders.MainViewHolder
import com.example.thousandsofcourses.presentation.mainscreen.model.CoursesItem
import com.example.thousandsofcourses.presentation.mainscreen.model.OnStateChanged
import com.example.thousandsofcourses.presentation.mainscreen.model.ViewItem

class CoursesAdapter(
    private val onStateChange: (OnStateChanged) -> Unit
): ListAdapter<ViewItem, RecyclerView.ViewHolder>(DiffCallback())  {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CoursesItem -> R.layout.course_item_layout
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            else -> {
                val binding = CourseItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CoursesItemViewHolder(
                    binding,
                    onStateChange
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        getItem(position)?.let { item ->
            (holder as MainViewHolder).bind(item)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
            (holder as MainViewHolder).bind(item, payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }


    companion object {
        const val LIKE_IMAGE = "Like"
        const val  LIKE_IMAGE_MEANING = 1
    }
}

class DiffCallback : DiffUtil.ItemCallback<ViewItem>() {
    override fun areItemsTheSame(oldItem:ViewItem, newItem: ViewItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ViewItem, newItem: ViewItem): Any? {
        val diffBundle = Bundle()
        return when{
            oldItem is CoursesItem && newItem is CoursesItem &&
                    (oldItem.hasLike != newItem.hasLike) &&
                    oldItem.id == newItem.id -> {
                diffBundle.apply {
                    putInt(CoursesAdapter.LIKE_IMAGE, CoursesAdapter.LIKE_IMAGE_MEANING)
                }
            }
            else -> super.getChangePayload(oldItem, newItem)
        }
    }


}


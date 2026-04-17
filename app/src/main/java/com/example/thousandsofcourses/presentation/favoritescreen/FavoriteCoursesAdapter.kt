package com.example.thousandsofcourses.presentation.favoritescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.R
import com.example.thousandsofcourses.databinding.CourseItemLayoutBinding
import com.example.thousandsofcourses.presentation.favoritescreen.holder.FavoriteCoursesItemViewHolder
import com.example.thousandsofcourses.presentation.favoritescreen.holder.MainViewHolder
import com.example.thousandsofcourses.presentation.favoritescreen.model.FavoriteCoursesItem
import com.example.thousandsofcourses.presentation.favoritescreen.model.OnStateChanged
import com.example.thousandsofcourses.presentation.favoritescreen.model.ViewItem

class FavoriteCoursesAdapter (
    private val onStateChange: (OnStateChanged) -> Unit
): ListAdapter<ViewItem, RecyclerView.ViewHolder>(DiffCallback())  {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FavoriteCoursesItem -> R.layout.course_item_layout
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
                return FavoriteCoursesItemViewHolder(
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
            oldItem is FavoriteCoursesItem && newItem is FavoriteCoursesItem &&
                    (oldItem.hasLike != newItem.hasLike) &&
                    oldItem.id == newItem.id -> {
                diffBundle.apply {
                    putInt(FavoriteCoursesAdapter.LIKE_IMAGE, FavoriteCoursesAdapter.LIKE_IMAGE_MEANING)
                }
            }
            else -> super.getChangePayload(oldItem, newItem)
        }
    }
}
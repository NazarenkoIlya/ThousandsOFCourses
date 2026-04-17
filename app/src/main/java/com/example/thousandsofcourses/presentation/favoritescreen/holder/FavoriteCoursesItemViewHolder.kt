package com.example.thousandsofcourses.presentation.favoritescreen.holder

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.R
import com.example.thousandsofcourses.databinding.CourseItemLayoutBinding
import com.example.thousandsofcourses.presentation.favoritescreen.FavoriteCoursesAdapter
import com.example.thousandsofcourses.presentation.favoritescreen.model.FavoriteCoursesItem
import com.example.thousandsofcourses.presentation.favoritescreen.model.OnLikeClicked
import com.example.thousandsofcourses.presentation.favoritescreen.model.OnStateChanged
import com.example.thousandsofcourses.presentation.favoritescreen.model.ViewItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FavoriteCoursesItemViewHolder (
    private val binding: CourseItemLayoutBinding,
    private val onStateChange: (OnStateChanged) -> Unit
) : RecyclerView.ViewHolder(binding.root), MainViewHolder {
    private val imageList = listOf(
        R.drawable.ic_image1,
        R.drawable.ic_image2,
        R.drawable.ic_image3
    )

    override fun bind(item: ViewItem) {
        item as FavoriteCoursesItem
        with(binding) {

            imageView.setImageResource(imageList[(0..imageList.lastIndex).random()])
            titleTextView.text = item.title
            descriptionTextView.text = item.text
            priceTextView.text = item.price
            dateTextView.text = formatDate(item.publishDate)
            favoriteImageView.setImageResource(
                if (item.hasLike) R.drawable.ic_fill_favorites
                else R.drawable.ic_favorites_small
            )
            favoriteImageView.setOnClickListener {
                onStateChange(OnLikeClicked(item.id))
            }
        }
    }


    override fun bind(item: ViewItem, payload: List<Any>) {

        (payload.firstOrNull() as? Bundle)?.let {
            val isContentChanged =
                it.getInt(FavoriteCoursesAdapter.LIKE_IMAGE) == FavoriteCoursesAdapter.LIKE_IMAGE_MEANING

            if (isContentChanged) {
                item as FavoriteCoursesItem
                binding.favoriteImageView.setImageResource(
                    if (item.hasLike) R.drawable.ic_fill_favorites
                    else R.drawable.ic_favorites_small
                )
            }
        }
    }


    private fun formatDate(inputDate: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(inputDate)

            val months = mapOf(
                1 to "Января", 2 to "Февраля", 3 to "Марта", 4 to "Апреля",
                5 to "Мая", 6 to "Июня", 7 to "Июля", 8 to "Августа",
                9 to "Сентября", 10 to "Октября", 11 to "Ноября", 12 to "Декабря"
            )

            val calendar = Calendar.getInstance()
            if (date != null) {
                calendar.time = date
            }

            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1
            val year = calendar.get(Calendar.YEAR)

            "$day ${months[month]} $year"
        } catch (e: Exception) {
            inputDate
        }
    }
}
package com.example.thousandsofcourses.presentation.holders

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.EditTextLayoutBinding
import com.example.thousandsofcourses.databinding.ImageBtnsLayoutBinding
import com.example.thousandsofcourses.presentation.model.EditTextItem
import com.example.thousandsofcourses.presentation.model.ImageButtonsItem
import com.example.thousandsofcourses.presentation.model.ViewItem
import androidx.core.net.toUri

class ImageButtonsItemHolder (
    private val binding:  ImageBtnsLayoutBinding,
): RecyclerView.ViewHolder(binding.root), MainViewHolder {

    init {
        with(binding) {
            okImageBtn.setOnClickListener { openBrowser(OK_URL) }
            vkImageBtn.setOnClickListener { openBrowser(VK_URL) }
        }
    }

    override fun bind(item: ViewItem) {}
    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
        binding.view.context.startActivity(browserIntent)
    }

    companion object {
        private const val OK_URL = "https://ok.ru/"
        private const val VK_URL = "https://vk.com/"
    }
}
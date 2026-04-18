package com.example.thousandsofcourses.presentation.login.holders

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.app.MainActivity
import com.example.thousandsofcourses.databinding.ButtonLayoutBinding
import com.example.thousandsofcourses.presentation.login.model.ButtonItem
import com.example.thousandsofcourses.presentation.login.model.OnButtonClick
import com.example.thousandsofcourses.presentation.login.model.OnStateChanged
import com.example.thousandsofcourses.presentation.login.model.UIButtonState
import com.example.thousandsofcourses.presentation.login.model.ViewItem

class ButtonItemHolder(
    private val binding: ButtonLayoutBinding,
    private val onStateChange: (OnStateChanged) -> Unit
) : RecyclerView.ViewHolder(binding.root), MainViewHolder {
    override fun bind(item: ViewItem) {
        item as ButtonItem

        binding.button.isEnabled = item.isEnable
        binding.button.setOnClickListener {
            onStateChange(OnButtonClick)
        }
        when (item.state) {
            UIButtonState.Idle -> {}
            UIButtonState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            UIButtonState.Success -> {
                binding.progressBar.visibility = View.GONE
                val intent = Intent(binding.root.context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                binding.root.context.startActivity(intent)
            }
        }
    }
}
package com.example.thousandsofcourses.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.ButtonLayoutBinding
import com.example.thousandsofcourses.databinding.EditTextLayoutBinding
import com.example.thousandsofcourses.databinding.ImageBtnsLayoutBinding
import com.example.thousandsofcourses.databinding.LineViewLayoutBinding
import com.example.thousandsofcourses.databinding.PasswordEditTextLayoutBinding
import com.example.thousandsofcourses.databinding.TextBtnsLayoutBinding
import com.example.thousandsofcourses.databinding.TitleLayoutBinding
import com.example.thousandsofcourses.databinding.TitleMediumLayoutBinding
import com.example.thousandsofcourses.presentation.login.holders.ButtonItemHolder
import com.example.thousandsofcourses.presentation.login.holders.EditTextItemHolder
import com.example.thousandsofcourses.presentation.login.holders.ImageButtonsItemHolder
import com.example.thousandsofcourses.presentation.login.holders.LineViewHolder
import com.example.thousandsofcourses.presentation.login.holders.MainViewHolder
import com.example.thousandsofcourses.presentation.login.holders.PasswordEditTextItemHolder
import com.example.thousandsofcourses.presentation.login.holders.TextButtonsItemHolder
import com.example.thousandsofcourses.presentation.login.holders.TitleItemViewHolder
import com.example.thousandsofcourses.presentation.login.holders.TitleMediumItemHolder
import com.example.thousandsofcourses.presentation.login.model.ButtonItem
import com.example.thousandsofcourses.presentation.login.model.EditTextItem
import com.example.thousandsofcourses.presentation.login.model.ImageButtonsItem
import com.example.thousandsofcourses.presentation.login.model.LineItem
import com.example.thousandsofcourses.presentation.login.model.OnStateChanged
import com.example.thousandsofcourses.presentation.login.model.PasswordEditTextItem
import com.example.thousandsofcourses.presentation.login.model.TextButtonsItem
import com.example.thousandsofcourses.presentation.login.model.TitleItem
import com.example.thousandsofcourses.presentation.login.model.TitleMediumItem
import com.example.thousandsofcourses.presentation.login.model.ViewItem

class LoginAdapter(
    private val onStateChange: (OnStateChanged) -> Unit
) : ListAdapter<ViewItem, RecyclerView.ViewHolder>(DiffCallback()) {


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TitleItem -> TYPE_TITLE
            is TitleMediumItem -> TYPE_TITLE_MEDIUM
            is EditTextItem -> TYPE_EDIT_TEXT
            is PasswordEditTextItem -> TYPE_PASSWORD
            is ButtonItem -> TYPE_BUTTON
            is TextButtonsItem -> TYPE_TEXT_BUTTONS
            is ImageButtonsItem -> TYPE_IMAGE_BUTTONS
            is LineItem -> TYPE_LINE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> {
                val binding = TitleLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TitleItemViewHolder(binding)
            }

            TYPE_TITLE_MEDIUM -> {
                val binding = TitleMediumLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TitleMediumItemHolder(binding)
            }

            TYPE_EDIT_TEXT -> {
                val binding = EditTextLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EditTextItemHolder(binding, onStateChange)
            }

            TYPE_PASSWORD -> {
                val binding = PasswordEditTextLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PasswordEditTextItemHolder(binding, onStateChange)
            }

            TYPE_BUTTON -> {

                val binding = ButtonLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ButtonItemHolder(binding)
            }

            TYPE_TEXT_BUTTONS -> {
                val binding = TextBtnsLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TextButtonsItemHolder(binding)
            }

            TYPE_IMAGE_BUTTONS -> {
                val binding = ImageBtnsLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ImageButtonsItemHolder(binding)
            }

            TYPE_LINE -> {
                val binding = LineViewLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LineViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
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
        private const val TYPE_TITLE = 1
        private const val TYPE_TITLE_MEDIUM = 2
        private const val TYPE_EDIT_TEXT = 3
        private const val TYPE_PASSWORD = 4
        private const val TYPE_BUTTON = 5
        private const val TYPE_TEXT_BUTTONS = 6
        private const val TYPE_IMAGE_BUTTONS = 7
        private const val TYPE_LINE = 8

        const val EMAIL_EDIT_TEXT = "Email"
        const val EMAIL_EDIT_TEXT_MEANING = 1

        const val PASSWORD_EDIT_TEXT = "Password"
        const val PASSWORD_EDIT_TEXT_MEANING = 2
    }
}

class DiffCallback : DiffUtil.ItemCallback<ViewItem>() {
    override fun areItemsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ViewItem, newItem: ViewItem): Any? {
        val diffBundle = Bundle()
        return when{
            oldItem is EditTextItem && newItem is EditTextItem &&
                    (oldItem.text != newItem.text) &&
                    oldItem.id == newItem.id -> {
                diffBundle.putInt(
                    LoginAdapter.EMAIL_EDIT_TEXT,
                    LoginAdapter.EMAIL_EDIT_TEXT_MEANING
                )
            }

            oldItem is PasswordEditTextItem && newItem is PasswordEditTextItem &&
                    (oldItem.text != newItem.text) &&
                    oldItem.id == newItem.id -> {
                diffBundle.putInt(
                    LoginAdapter.PASSWORD_EDIT_TEXT,
                    LoginAdapter.PASSWORD_EDIT_TEXT_MEANING
                )
            }

            else -> super.getChangePayload(oldItem, newItem)
        }
    }
}

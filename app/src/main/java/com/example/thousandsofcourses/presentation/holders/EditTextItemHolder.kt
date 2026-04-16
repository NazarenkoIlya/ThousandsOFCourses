package com.example.thousandsofcourses.presentation.holders

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandsofcourses.databinding.EditTextLayoutBinding
import com.example.thousandsofcourses.databinding.PasswordEditTextLayoutBinding
import com.example.thousandsofcourses.presentation.LoginAdapter
import com.example.thousandsofcourses.presentation.model.EditTextItem
import com.example.thousandsofcourses.presentation.model.OnEmailChanged
import com.example.thousandsofcourses.presentation.model.OnPasswordChanged
import com.example.thousandsofcourses.presentation.model.OnStateChanged
import com.example.thousandsofcourses.presentation.model.PasswordEditTextItem
import com.example.thousandsofcourses.presentation.model.ViewItem

class EditTextItemHolder(
    private val binding: EditTextLayoutBinding,
    private val onStateChange: (OnStateChanged) -> Unit
) : RecyclerView.ViewHolder(binding.root), MainViewHolder {


    private var currentText = ""
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val newText = s?.toString() ?: ""
            if (currentText == newText) return
            currentText = newText
            onStateChange(OnEmailChanged(newText))
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun bind(item: ViewItem) {
        item as EditTextItem
        if (binding.editText.text.toString() != item.text) {
            currentText = item.text
            binding.editText.setText(item.text)
            binding.editText.setSelection(item.text.length)

        }
        binding.editText.addTextChangedListener(textWatcher)
    }

    override fun bind(item: ViewItem, payload: List<Any>) {
        (payload.firstOrNull() as? Bundle)?.let {
            val isContentChanged =
                it.getInt(LoginAdapter.EMAIL_EDIT_TEXT) == LoginAdapter.EMAIL_EDIT_TEXT_MEANING

            if (isContentChanged) {
                  item as EditTextItem
                if (binding.editText.text.toString() != item.text) {
                    currentText = item.text
                    binding.editText.setText(item.text)
                    binding.editText.setSelection(item.text.length)

                }
                binding.editText.addTextChangedListener(textWatcher)
            }
        }
    }

}
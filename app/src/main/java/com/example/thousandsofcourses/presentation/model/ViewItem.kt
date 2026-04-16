package com.example.thousandsofcourses.presentation.model


sealed interface ViewItem {
    val id: String
}

data class TitleItem(
    override val id: String,
    val text: String,
):  ViewItem

data class TitleMediumItem(
    override val id: String,
    val text: String,
):  ViewItem

data class TextButtonsItem(
    override val id: String
):  ViewItem

data class PasswordEditTextItem(
    override val id: String,
    val text: String,
    val isCorrect: Boolean
):  ViewItem

data class EditTextItem(
    override val id: String,
    val text: String,
    val isCorrect: Boolean
):  ViewItem

data class ButtonItem(
    override val id: String,
    val state: UIButtonState = UIButtonState.Idle,
    val isEnable: Boolean = false
):  ViewItem

data class ImageButtonsItem(
    override val id: String
):   ViewItem

data class LineItem(
    override val id: String
):   ViewItem

sealed class UIButtonState{
    object Idle: UIButtonState()
    object Loading: UIButtonState()
    object Success: UIButtonState()
}

data class UIState(
    val viewItems: List<ViewItem> = emptyList()
)

enum class Ids(val idName: String) {
    Title("title"),
    TitleMedium("titleMedium"),
    TextButtons("textBtns"),
    PasswordEditText("passwordEditText"),
    EditText("editText"),
    Button("button"),
    ImageButton("imageButton"),
    Line("line"),
}


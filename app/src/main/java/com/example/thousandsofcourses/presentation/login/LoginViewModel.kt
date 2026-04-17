package com.example.thousandsofcourses.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandsofcourses.R
import com.example.thousandsofcourses.manager.ResourcesManager
import com.example.thousandsofcourses.presentation.login.model.ButtonItem
import com.example.thousandsofcourses.presentation.login.model.EditTextItem
import com.example.thousandsofcourses.presentation.login.model.Ids
import com.example.thousandsofcourses.presentation.login.model.ImageButtonsItem
import com.example.thousandsofcourses.presentation.login.model.LineItem
import com.example.thousandsofcourses.presentation.login.model.OnButtonClick
import com.example.thousandsofcourses.presentation.login.model.OnEmailChanged
import com.example.thousandsofcourses.presentation.login.model.OnPasswordChanged
import com.example.thousandsofcourses.presentation.login.model.OnStateChanged
import com.example.thousandsofcourses.presentation.login.model.PasswordEditTextItem
import com.example.thousandsofcourses.presentation.login.model.TextButtonsItem
import com.example.thousandsofcourses.presentation.login.model.TitleItem
import com.example.thousandsofcourses.presentation.login.model.TitleMediumItem
import com.example.thousandsofcourses.presentation.login.model.UIButtonState
import com.example.thousandsofcourses.presentation.login.model.UIState
import com.example.thousandsofcourses.presentation.login.model.ViewItem
import com.example.thousandsofcourses.utils.Validator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.mutableListOf

@OptIn(FlowPreview::class)
class LoginViewModel(
    val resourcesManager: ResourcesManager
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state
    private val _events = MutableSharedFlow<OnStateChanged>(replay = 1)

    init {
        _events
            .filterIsInstance<OnEmailChanged>()
            .map { it.text }
            .also {
                Log.d("AAAAAA", "${it}")
            }
            .debounce(300)
            .filter { it.length >= 3 }
            .onEach { email ->
                validateAndUpdateEmailState(email)
            }
            .launchIn(viewModelScope)

        _events
            .filterIsInstance<OnPasswordChanged>()
            .map { it.text }
            .debounce(300)
            .filter { it.length >= 3 }
            .onEach { password ->
                validateAndUpdatePasswordState(password)
            }
            .launchIn(viewModelScope)


        loadState()
    }

    private fun loadState() {
        viewModelScope.launch {
            val viewItem = mutableListOf<ViewItem>()
            val title = TitleItem(
                id = Ids.Title.idName,
                text = resourcesManager.getString(R.string.entrance)
            )
            viewItem.add(title)
            val emailTitle = TitleMediumItem(
                id = Ids.TitleMedium.idName,
                text = resourcesManager.getString(R.string.email)
            )
            viewItem.add(emailTitle)
            val emailEditText = EditTextItem(
                id = Ids.EditText.idName,
                text = "",
                isCorrect = false
            )
            viewItem.add(emailEditText)
            val passwordTitle = TitleMediumItem(
                id = Ids.TitleMedium.idName + 1,
                text = resourcesManager.getString(R.string.password)
            )
            viewItem.add(passwordTitle)
            val passwordEditText = PasswordEditTextItem(
                id = Ids.PasswordEditText.idName,
                text = "",
                isCorrect = false
            )
            viewItem.add(passwordEditText)
            val button = ButtonItem(
                id = Ids.Button.idName,
            )
            viewItem.add(button)
            val textBtns = TextButtonsItem(
                id = Ids.TextButtons.idName
            )
            viewItem.add(textBtns)

            val line = LineItem(
                id = Ids.Line.idName
            )
            viewItem.add(line)
            val imageButtons = ImageButtonsItem(
                id = Ids.ImageButton.idName
            )
            viewItem.add(imageButtons)

            _state.update {
                it.copy(viewItems = viewItem)
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun onEvent(onStateChanged: OnStateChanged) {
        when (onStateChanged) {
            is OnEmailChanged, is OnPasswordChanged -> _events.tryEmit(onStateChanged)
            OnButtonClick -> {
                SignIn()
            }
        }

    }
    private fun SignIn(){
        viewModelScope.launch {
            _state.update {
                it.copy(it.viewItems.map {
                    if (it is ButtonItem) {
                        it.copy(state = UIButtonState.Loading)
                    } else it
                })
            }
            delay(1000)
            _state.update {
                it.copy(it.viewItems.map {
                    if (it is ButtonItem) {
                        it.copy(state = UIButtonState.Success)
                    } else it
                })
            }
        }
    }

    private fun getIndices(viewItems: List<ViewItem>): Triple<Int, Int, Int> {
        return Triple(
            viewItems.indexOfFirst { it.id == Ids.EditText.idName },
            viewItems.indexOfFirst { it.id == Ids.PasswordEditText.idName },
            viewItems.indexOfFirst { it.id == Ids.Button.idName }
        )
    }

    private fun validateAndUpdateEmailState(email: String) {
        _state.update { currentState ->
            val viewItems = currentState.viewItems.toMutableList()
            val (emailIndex, passwordIndex, buttonIndex) = getIndices(viewItems)

            val isEmailValid = Validator.validateEmail(email) == null && email.isNotEmpty()
            val passwordItem = viewItems.getOrNull(passwordIndex) as? PasswordEditTextItem
            val isPasswordValid = passwordItem?.isCorrect == true


            (viewItems[emailIndex] as? EditTextItem)?.let {
                viewItems[emailIndex] = it.copy(text = email, isCorrect = isEmailValid)
            }

            (viewItems[buttonIndex] as? ButtonItem)?.let {
                viewItems[buttonIndex] = it.copy(isEnable = isEmailValid && isPasswordValid)
            }

            currentState.copy(viewItems = viewItems)
        }
    }

    private fun validateAndUpdatePasswordState(password: String) {
        _state.update { currentState ->
            val viewItems = currentState.viewItems.toMutableList()
            val (emailIndex, passwordIndex, buttonIndex) = getIndices(viewItems)

            val isPasswordValid =
                Validator.validatePassword(password) == null && password.isNotEmpty()
            val emailItem = viewItems.getOrNull(emailIndex) as? EditTextItem
            val isEmailValid = emailItem?.isCorrect == true

            (viewItems[passwordIndex] as? PasswordEditTextItem)?.let {
                viewItems[passwordIndex] = it.copy(text = password, isCorrect = isPasswordValid)
            }

            (viewItems[buttonIndex] as? ButtonItem)?.let {
                viewItems[buttonIndex] = it.copy(isEnable = isEmailValid && isPasswordValid)
            }

            currentState.copy(viewItems = viewItems)
        }
    }
}
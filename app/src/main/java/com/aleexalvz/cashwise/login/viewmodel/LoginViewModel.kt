package com.aleexalvz.cashwise.login.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aleexalvz.cashwise.data.mocked.login.getMockedAuthRepository
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.helper.AuthHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

const val REMEMBER_ME_LOGIN_KEY = "REMEMBER_ME_LOGIN_KEY"
const val SharedPreferences_LOGIN_KEY = "REMEMBER_ME_LOGIN_KEY"

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false
)

class LoginViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val authRepository: AuthRepository by lazy {
        application.getMockedAuthRepository()
    }

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    init {
        val sharedPreferences =
            application.getSharedPreferences(SharedPreferences_LOGIN_KEY, Context.MODE_PRIVATE)

        sharedPreferences.getString(REMEMBER_ME_LOGIN_KEY, null)?.let {
            updateEmail(it)
        }
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        if (AuthHelper.validateEmail(email).not()) {
            updateEmailError(true)
        } else updateEmailError(false)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        if (AuthHelper.validatePassword(password).not()) {
            updatePasswordError(true)
        } else updatePasswordError(false)
    }

    private fun updateEmailError(boolean: Boolean) {
        _uiState.value = _uiState.value.copy(emailError = boolean)
    }

    private fun updatePasswordError(boolean: Boolean) {
        _uiState.value = _uiState.value.copy(passwordError = boolean)
    }

    fun doLogin() {
        if (!uiState.value.emailError && !uiState.value.passwordError) run {
            authRepository.doLogin(uiState.value.email, uiState.value.password)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                LoginViewModel(application)
            }
        }
    }
}
package com.aleexalvz.cashwise.feature.login.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aleexalvz.cashwise.data.mocked.login.getMockedAuthRepository
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.foundation.UserManager
import com.aleexalvz.cashwise.helper.AuthHelper
import com.aleexalvz.cashwise.model.UserNotFoundException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val REMEMBER_ME_EMAIL_KEY = "REMEMBER_ME_LOGIN_KEY"
const val SharedPreferences_LOGIN_KEY = "REMEMBER_ME_LOGIN_KEY"

/**
 * Data that represents UI State from Login Screen
 * */
data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val loginState: Boolean = false
)

class LoginViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val authRepository: AuthRepository by lazy {
        application.getMockedAuthRepository()
    }

    private var sharedPreferences: SharedPreferences

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    init {
        sharedPreferences =
            application.getSharedPreferences(SharedPreferences_LOGIN_KEY, Context.MODE_PRIVATE)
        sharedPreferences.getString(REMEMBER_ME_EMAIL_KEY, null)?.let {
            updateEmail(it)
            _uiState.value = _uiState.value.copy(rememberMe = true)
        }
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        updateEmailError()
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        updatePasswordError()
    }

    fun updateRememberMeCheckBox(value: Boolean){
        _uiState.value = _uiState.value.copy(rememberMe = value)
    }

    private fun updateEmailError(error: String? = null) {
        val message: String? = if (error.isNullOrBlank().not()) {
            error
        } else if (uiState.value.email.isNotBlank() && AuthHelper.validateEmail(uiState.value.email)
                .not()
        )
            "Invalid email"
        else null
        _uiState.value = _uiState.value.copy(emailError = message)
    }

    private fun updatePasswordError(error: String? = null) {
        val message: String? = if (error.isNullOrBlank().not()) {
            error
        } else if (uiState.value.password.isNotBlank() && AuthHelper.validatePassword(uiState.value.password)
                .not()
        )
            "Invalid password"
        else null
        _uiState.value = _uiState.value.copy(passwordError = message)
    }

    private fun updateLoginStateToSuccess() {
        _uiState.value = _uiState.value.copy(loginState = true)
    }

    fun doLogin() {
        if (isValidFields()) {
            viewModelScope.launch {
                try {
                    val result = authRepository.doLogin(uiState.value.email, uiState.value.password)
                    UserManager.loggedUser = result
                    verifyRememberUser()
                    updateLoginStateToSuccess()
                } catch (userNotFoundException: UserNotFoundException) {
                    Toast
                        .makeText(
                            getApplication(),
                            userNotFoundException.message,
                            Toast.LENGTH_LONG
                        )
                        .show()
                }
            }
        }
    }

    private fun verifyRememberUser() {
        if (uiState.value.rememberMe) {
            sharedPreferences.edit().putString(REMEMBER_ME_EMAIL_KEY, uiState.value.email).apply()
        }
    }

    private fun isValidFields(): Boolean = with(uiState.value) {
        var hasNoError = this.emailError.isNullOrBlank() &&
                this.passwordError.isNullOrBlank()

        if (this.email.isBlank()) {
            hasNoError = false
            updateEmailError("Required field, insert your email")
        } else updateEmailError()

        if (this.password.isBlank()) {
            hasNoError = false
            updatePasswordError("Required field, insert your password")
        } else updatePasswordError()
        return hasNoError
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
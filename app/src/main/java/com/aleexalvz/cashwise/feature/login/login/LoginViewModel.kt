package com.aleexalvz.cashwise.feature.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.foundation.UserManager
import com.aleexalvz.cashwise.helper.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    init {
        authRepository.verifyRememberUser()?.let {
            updateEmail(it)
            _uiState.value = _uiState.value.copy(rememberMe = true)
        }
    }

    fun onUIAction(uiAction: LoginUIAction) {
        when (uiAction) {
            is LoginUIAction.UpdateEmail -> updateEmail(uiAction.email)
            is LoginUIAction.UpdatePassword -> updatePassword(uiAction.password)
            is LoginUIAction.UpdateRememberMeCheckBox -> updateRememberMeCheckBox(uiAction.rememberMe)
        }
    }

    private fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        updateEmailError()
    }

    private fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        updatePasswordError()
    }

    private fun updateRememberMeCheckBox(value: Boolean) {
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
        viewModelScope.launch {
            runCatching {
                if (isValidFields()) {
                    val result = authRepository.doLogin(uiState.value.email, uiState.value.password)
                    UserManager.loggedUser = result
                    verifyRememberUser()
                    updateLoginStateToSuccess()
                }
            }
        }
    }

    private fun verifyRememberUser() {
        if (uiState.value.rememberMe) {
            authRepository.rememberUser(uiState.value.email)
        } else {
            authRepository.forgetUser()
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
}
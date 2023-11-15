package com.aleexalvz.cashwise.feature.login.signup

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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState.asStateFlow()

    fun onUIAction(uiAction: SignUpUIAction) {
        when (uiAction) {
            is SignUpUIAction.UpdateEmail -> updateEmail(uiAction.email)
            is SignUpUIAction.UpdatePassword -> updateEmail(uiAction.password)
            is SignUpUIAction.UpdateConfirmPassword -> updateEmail(uiAction.confirmPassword)
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

    private fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
        updateConfirmPasswordError()
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

    private fun updateConfirmPasswordError(error: String? = null) {
        val message: String? = if (error.isNullOrBlank().not()) {
            error
        } else if (uiState.value.confirmPassword.isNotBlank() && uiState.value.password != uiState.value.confirmPassword)
            "Should be equals your inserted password"
        else null
        _uiState.value = _uiState.value.copy(confirmPasswordError = message)
    }

    private fun updateSignUpStateToSuccess() {
        _uiState.value = _uiState.value.copy(signUpState = true)
    }

    fun doSignup() {
        viewModelScope.launch {
            runCatching { }
            if (isValidFields()) {
                val result = authRepository.signupUser(uiState.value.email, uiState.value.password)
                UserManager.loggedUser = result
                updateSignUpStateToSuccess()
            }
        }
    }

    private fun isValidFields(): Boolean = with(uiState.value) {
        var hasNoError = this.emailError.isNullOrBlank() &&
                this.passwordError.isNullOrBlank() &&
                this.confirmPasswordError.isNullOrBlank()

        if (this.email.isBlank()) {
            hasNoError = false
            updateEmailError("Required field, insert your email")
        }

        if (this.password.isBlank()) {
            hasNoError = false
            updatePasswordError("Required field, insert your password")
        }

        if (this.confirmPassword.isBlank()) {
            hasNoError = false
            updateConfirmPasswordError("Required field, confirm your password")
        }

        return hasNoError
    }
}

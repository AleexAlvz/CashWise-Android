package com.aleexalvz.cashwise.feature.login.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aleexalvz.cashwise.data.mocked.login.getMockedAuthRepository
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.foundation.UserManager
import com.aleexalvz.cashwise.helper.AuthHelper
import com.aleexalvz.cashwise.model.SignUpInvalidException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Data that represents UI State from SignUp Screen
 * */
data class SignUpUIState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val confirmPasswordError: Boolean = false,
    val signUpState: Boolean = false
)

class SignUpViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val authRepository: AuthRepository by lazy {
        application.getMockedAuthRepository()
    }

    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        val hasError = AuthHelper.validateEmail(email).not()
        updateEmailError(hasError)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        val hasError = AuthHelper.validatePassword(password).not()
        updatePasswordError(hasError)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
        val hasError = confirmPassword != uiState.value.password
        updateConfirmPasswordError(hasError)
    }

    private fun updateEmailError(boolean: Boolean) {
        _uiState.value = _uiState.value.copy(emailError = boolean)
    }

    private fun updatePasswordError(boolean: Boolean) {
        _uiState.value = _uiState.value.copy(passwordError = boolean)
    }

    private fun updateConfirmPasswordError(boolean: Boolean) {
        _uiState.value = _uiState.value.copy(confirmPasswordError = boolean)
    }

    private fun updateSignUpStateToSuccess() {
        _uiState.value = _uiState.value.copy(signUpState = true)
    }

    fun signupUser() {
        val isValidUser = with(uiState.value) {
            this.email.isNotBlank() &&
                    this.password.isNotBlank() &&
                    this.confirmPassword.isNotBlank() &&
                    this.emailError.not() &&
                    this.passwordError.not() &&
                    this.confirmPasswordError.not()
        }

        if (isValidUser) {
            try {
                val result = authRepository.signupUser(uiState.value.email, uiState.value.password)
                UserManager.loggedUser = result
                updateSignUpStateToSuccess()
            } catch (signUpInvalidException: SignUpInvalidException) {
                Toast
                    .makeText(getApplication(), signUpInvalidException.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)
                SignUpViewModel(application)
            }
        }
    }
}

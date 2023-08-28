package com.aleexalvz.cashwise.feature.login.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

/**
 * Data that represents UI State from SignUp Screen
 * */
data class SignUpUIState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
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
        updateEmailError()
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        updatePasswordError()
    }

    fun updateConfirmPassword(confirmPassword: String) {
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

    fun signupUser() {
        if (isValidFields()) {
            viewModelScope.launch {
                try {
                    val result =
                        authRepository.signupUser(uiState.value.email, uiState.value.password)
                    UserManager.loggedUser = result
                    updateSignUpStateToSuccess()
                } catch (signUpInvalidException: SignUpInvalidException) {
                    Toast
                        .makeText(
                            getApplication(),
                            signUpInvalidException.message,
                            Toast.LENGTH_LONG
                        )
                        .show()
                }
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

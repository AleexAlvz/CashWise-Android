package com.aleexalvz.cashwise.feature.login.data

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
)
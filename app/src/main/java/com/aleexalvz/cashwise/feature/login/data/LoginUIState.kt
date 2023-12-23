package com.aleexalvz.cashwise.feature.login.data

/**
 * Data that represents UI State from Login Screen
 * */
data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
)
package com.aleexalvz.cashwise.feature.login.login

sealed class LoginUIAction {
    data class UpdateEmail(val email: String): LoginUIAction()
    data class UpdatePassword(val password: String): LoginUIAction()
    data class UpdateRememberMeCheckBox(val rememberMe: Boolean): LoginUIAction()
}

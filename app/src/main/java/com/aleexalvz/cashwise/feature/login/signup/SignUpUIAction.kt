package com.aleexalvz.cashwise.feature.login.signup

sealed class SignUpUIAction {
    data class UpdateEmail(val email: String): SignUpUIAction()
    data class UpdatePassword(val password: String): SignUpUIAction()
    data class UpdateConfirmPassword(val confirmPassword: String): SignUpUIAction()
}

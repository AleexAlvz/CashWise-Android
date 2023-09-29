package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.auth.User

interface AuthRepository {
    fun doLogin(email: String, password: String): User
    fun signupUser(email: String, password: String): User
    fun verifyRememberUser(): String?
    fun rememberUser(email: String)
    fun forgetUser()
}
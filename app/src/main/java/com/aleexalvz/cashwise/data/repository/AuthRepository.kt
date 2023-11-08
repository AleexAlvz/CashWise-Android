package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.auth.User

interface AuthRepository {
    suspend fun doLogin(email: String, password: String): User
    suspend fun signupUser(email: String, password: String): User
    fun verifyRememberUser(): String?
    fun rememberUser(email: String)
    fun forgetUser()
}
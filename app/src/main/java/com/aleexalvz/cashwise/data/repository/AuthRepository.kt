package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.user.User

interface AuthRepository {
    fun doLogin(email: String, password: String): User
    fun signupUser(email: String, password: String): User
}
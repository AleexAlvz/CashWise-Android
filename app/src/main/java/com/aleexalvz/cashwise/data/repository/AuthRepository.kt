package com.aleexalvz.cashwise.data.repository

interface AuthRepository {
    fun doLogin(email: String, password: String): Boolean
    fun signupUser(email: String, password: String): Boolean
}
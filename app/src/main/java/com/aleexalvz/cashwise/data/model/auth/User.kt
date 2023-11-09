package com.aleexalvz.cashwise.data.model.auth

data class User(
    val userID: Long = 0,
    val email: String,
    val password: String,
    val name: String = "",
    val phone: String = ""
)
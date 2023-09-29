package com.aleexalvz.cashwise.data.model.auth

data class User(
    val email: String,
    val name: String? = null,
    val phone: String? = null
)
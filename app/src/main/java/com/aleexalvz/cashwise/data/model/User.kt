package com.aleexalvz.cashwise.data.model

data class User(
    val email: String,
    val name: String? = null,
    val phone: String? = null
)
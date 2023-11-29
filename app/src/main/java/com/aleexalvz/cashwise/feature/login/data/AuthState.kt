package com.aleexalvz.cashwise.feature.login.data

import com.aleexalvz.cashwise.data.model.auth.User

sealed interface AuthState {
    data class OnSuccess(val user: User) : AuthState
    data class OnFailure(val throwable: Throwable) : AuthState
}
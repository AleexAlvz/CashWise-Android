package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.source.local.model.LocalUser

interface LocalUserRepository {
    suspend fun getByEmail(email: String): LocalUser

    suspend fun register(user: LocalUser)

    suspend fun update(user: LocalUser)
}
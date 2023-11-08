package com.aleexalvz.cashwise.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleexalvz.cashwise.data.model.auth.User

@Entity
data class LocalUser(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String,
    val password: String,
    val name: String? = null,
    val phone: String? = null
)

fun LocalUser.toUser(): User = User(
    userID = id, email = email, password = password, name = name, phone = phone
)

fun User.toLocalUser(): LocalUser = LocalUser(
    id = userID, email = email, password = password, name = name, phone = phone
)
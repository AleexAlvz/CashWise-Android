package com.aleexalvz.cashwise.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aleexalvz.cashwise.data.source.local.model.LocalUser

@Dao
interface UserDao {
    @Query("SELECT * FROM `LOCALUSER` WHERE email == (:email)")
    fun getByEmail(email: String): LocalUser

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun register(user: LocalUser)

    @Update
    fun update(user: LocalUser)
}
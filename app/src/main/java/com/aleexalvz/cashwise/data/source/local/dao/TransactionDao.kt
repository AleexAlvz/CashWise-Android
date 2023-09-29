package com.aleexalvz.cashwise.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `LOCALTRANSACTION`")
    fun getAll(): List<LocalTransaction>

    @Query("SELECT * FROM `LOCALTRANSACTION` WHERE id == (:id)")
    fun getByID(id: Long): LocalTransaction

    @Insert
    fun insert(transaction: LocalTransaction)

    @Delete
    fun delete(transaction: LocalTransaction)

    @Update
    fun update(transaction: LocalTransaction)

}
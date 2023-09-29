package com.aleexalvz.cashwise.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aleexalvz.cashwise.data.model.transaction.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `LOCALTRANSACTION`")
    fun getAll(): List<Transaction>

    @Query("SELECT * FROM `LOCALTRANSACTION` WHERE id == (:id)")
    fun getByID(id: Long): Transaction

    @Insert
    fun insert(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

}
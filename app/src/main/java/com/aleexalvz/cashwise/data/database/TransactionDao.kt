package com.aleexalvz.cashwise.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `TRANSACTION`")
    fun getAll()

    @Query("SELECT * FROM `TRANSACTION` WHERE id == (:id)")
    fun getByID(id: Long)

    @Query("SELECT * FROM `TRANSACTION` WHERE category == (:category)")
    fun getByCategory(category: TransactionCategory)

    @Insert
    fun insert(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

}
package com.aleexalvz.cashwise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aleexalvz.cashwise.data.model.transaction.Transaction

@Database(entities = [Transaction::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
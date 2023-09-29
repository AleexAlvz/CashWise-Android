package com.aleexalvz.cashwise.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction
import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao

@Database(entities = [LocalTransaction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
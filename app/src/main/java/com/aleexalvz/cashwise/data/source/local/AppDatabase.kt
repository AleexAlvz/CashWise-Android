package com.aleexalvz.cashwise.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction
import com.aleexalvz.cashwise.data.source.local.model.LocalUser
import com.aleexalvz.cashwise.data.source.local.typeconverter.DateConverter

@Database(entities = [LocalTransaction::class, LocalUser::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao
}
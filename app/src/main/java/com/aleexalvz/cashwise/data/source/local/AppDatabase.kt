package com.aleexalvz.cashwise.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleexalvz.cashwise.data.source.local.dao.InvestmentDao
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import com.aleexalvz.cashwise.data.source.local.model.LocalInvestment
import com.aleexalvz.cashwise.data.source.local.model.LocalUser
import com.aleexalvz.cashwise.data.source.local.typeconverter.DateConverter

@Database(entities = [LocalInvestment::class, LocalUser::class], version = 4)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun investmentDao(): InvestmentDao
    abstract fun userDao(): UserDao
}
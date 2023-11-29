package com.aleexalvz.cashwise.di

import com.aleexalvz.cashwise.data.repository.TransactionRepository
import com.aleexalvz.cashwise.data.source.local.repository.LocalTransactionRepositoryImpl
import com.aleexalvz.cashwise.data.source.local.AppDatabase
import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {

    @Provides
    fun providesTransactionDao(database: AppDatabase): TransactionDao = database.transactionDao()

    @Provides
    fun providesLocalTransactionRepository(transactionDao: TransactionDao): TransactionRepository =
        LocalTransactionRepositoryImpl(transactionDao)
}

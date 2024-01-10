package com.aleexalvz.cashwise.di

import com.aleexalvz.cashwise.data.repository.LocalInvestmentRepository
import com.aleexalvz.cashwise.data.repository.LocalInvestmentRepositoryImpl
import com.aleexalvz.cashwise.data.source.local.AppDatabase
import com.aleexalvz.cashwise.data.source.local.dao.InvestmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InvestmentsModule {

    @Provides
    fun providesInvestmentDao(database: AppDatabase): InvestmentDao = database.investmentDao()

    @Provides
    fun providesLocalInvestmentRepository(investmentDao: InvestmentDao): LocalInvestmentRepository =
        LocalInvestmentRepositoryImpl(investmentDao)
}

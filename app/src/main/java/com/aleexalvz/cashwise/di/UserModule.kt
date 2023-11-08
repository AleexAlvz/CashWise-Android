package com.aleexalvz.cashwise.di

import com.aleexalvz.cashwise.data.repository.LocalUserRepository
import com.aleexalvz.cashwise.data.repository.LocalUserRepositoryImpl
import com.aleexalvz.cashwise.data.source.local.AppDatabase
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun providesUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun providesLocalUserRepository(userDao: UserDao): LocalUserRepository =
        LocalUserRepositoryImpl(userDao)
}

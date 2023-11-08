package com.aleexalvz.cashwise.di

import android.content.Context
import android.content.SharedPreferences
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.data.repository.AuthRepositoryImpl
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    private const val SharedPreferences_Auth_Key = "REMEMBER_ME_LOGIN_KEY"

    @Provides
    fun providesAuthSharedPreferences(
        @ApplicationContext appContext: Context
    ): SharedPreferences =
        appContext.getSharedPreferences(SharedPreferences_Auth_Key, Context.MODE_PRIVATE)

    @Provides
    fun providesAuthRepository(
        userDao: UserDao,
        sharedPreferences: SharedPreferences
    ): AuthRepository = AuthRepositoryImpl(userDao, sharedPreferences)
}
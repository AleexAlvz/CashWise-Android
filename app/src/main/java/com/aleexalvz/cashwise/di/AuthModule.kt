package com.aleexalvz.cashwise.di

import android.content.Context
import android.content.SharedPreferences
import com.aleexalvz.cashwise.data.mocked.auth.MockedAuthRepository
import com.aleexalvz.cashwise.data.repository.AuthRepository
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
        @ApplicationContext appContext: Context,
        sharedPreferences: SharedPreferences
    ): AuthRepository = MockedAuthRepository(appContext, sharedPreferences)


}
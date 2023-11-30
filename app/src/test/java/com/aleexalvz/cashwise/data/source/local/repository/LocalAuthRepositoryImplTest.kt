package com.aleexalvz.cashwise.data.source.local.repository

import android.content.SharedPreferences
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import com.aleexalvz.cashwise.data.source.local.model.LocalUser
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LocalAuthRepositoryImplTest {

    private val userDao: UserDao = mockk()
    private val sharedPreferences: SharedPreferences = mockk()
    private val localAuthRepositoryImpl = LocalAuthRepositoryImpl(userDao, sharedPreferences)

    private val email = "email@email.com"
    private val password = "teste123"

    @AfterEach
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `when doLogin but user not exists then throws exception`(): Unit = runBlocking {
        //Arrange
        every { userDao.getByEmail(email) }.returns(null)

        //Act
        val result = localAuthRepositoryImpl.doLogin(email, password)

        //Assert
        val errorMessage = result.exceptionOrNull()?.message.toString()
        Assertions.assertEquals(LocalAuthRepositoryImpl.USER_NOT_FOUND, errorMessage)
    }

    @Test
    fun `when doLogin but password is wrong then throws exception`(): Unit = runBlocking {
        //Arrange
        every { userDao.getByEmail(email) }.returns(
            LocalUser(
                email = email,
                password = "wrongpassword"
            )
        )

        //Act
        val result = localAuthRepositoryImpl.doLogin(email, password)

        //Assert
        val errorMessage = result.exceptionOrNull()?.message.toString()
        Assertions.assertEquals(LocalAuthRepositoryImpl.WRONG_PASSWORD, errorMessage)
    }

    @Test
    fun `when doLogin and all fine then returns user`(): Unit = runBlocking {
        //Arrange
        every { userDao.getByEmail(email) }.returns(LocalUser(email = email, password = password))

        //Act
        val result = localAuthRepositoryImpl.doLogin(email, password)

        //Assert
        val user = result.getOrNull()
        Assertions.assertNotNull(user)
    }
}
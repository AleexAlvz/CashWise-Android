package com.aleexalvz.cashwise.data.source.local.repository

import android.content.SharedPreferences
import com.aleexalvz.cashwise.data.model.auth.InvalidUserPasswordException
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
        every { userDao.getByEmail(email) }.throws(Exception())

        //Act
        val exception = assertThrows<InvalidUserPasswordException> {
            localAuthRepositoryImpl.doLogin(email, password)
        }

        //Assert
        Assertions.assertEquals("User not found", exception.message)
    }

}
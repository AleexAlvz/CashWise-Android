package com.aleexalvz.cashwise.data.source.local.repository

import com.aleexalvz.cashwise.data.model.auth.User
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao
import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction
import com.aleexalvz.cashwise.data.source.local.model.toTransaction
import com.aleexalvz.cashwise.foundation.UserManager
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.Date

class LocalTransactionRepositoryImplTest {

    private val transactionDao: TransactionDao = mockk()
    private val localTransactionRepositoryImpl = LocalTransactionRepositoryImpl(transactionDao)

    @AfterEach
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `when getAll success then returns transactions by user`() = runBlocking {
        //Arrange
        mockUser(isValid = true)
        val mockedList = getMockedLocalTransactionList()
        every { transactionDao.getByUserID(any()) } returns mockedList

        //Act
        val result = localTransactionRepositoryImpl.getAll()

        //Assert
        assertNotNull(result.getOrNull())
        assertEquals(mockedList.map { it.toTransaction() }, result.getOrNull())
        assertEquals(mockedList.size, result.getOrNull()?.size)
    }

    @Test
    fun `when getAll fails cause user not found then returns failure`() = runBlocking {
        //Arrange
        mockUser(isValid = false)
        val mockedList = getMockedLocalTransactionList()
        every { transactionDao.getByUserID(any()) } returns mockedList

        //Act
        val result = localTransactionRepositoryImpl.getAll()

        //Assert
        assertNotNull(result.exceptionOrNull())
        assertEquals(
            LocalTransactionRepositoryImpl.USER_NOT_FOUND_ERROR,
            result.exceptionOrNull()?.message.toString()
        )
    }

    @Test
    fun `when getAll success fetching empty list`() = runBlocking {
        //Arrange
        mockUser(isValid = true)
        every { transactionDao.getByUserID(any()) } returns listOf()

        //Act
        val result = localTransactionRepositoryImpl.getAll()

        //Assert
        assertNotNull(result.getOrNull())
        assertEquals(0, result.getOrNull()?.size)
    }

    @Test
    fun `when getByID success then returns transaction`() = runBlocking {
        //Arrange
        val mockedLocalTransaction = getMockedLocalTransaction()
        every { transactionDao.getByID(any()) } returns mockedLocalTransaction

        //Act
        val result = localTransactionRepositoryImpl.getByID(1L)

        //Assert
        assertNotNull(result.getOrNull())
        assertEquals(mockedLocalTransaction.toTransaction(), result.getOrNull())
    }

    @Test
    fun `when getByID fails then returns exception`() = runBlocking {
        //Arrange
        every { transactionDao.getByID(any()) } throws Exception()

        //Act
        val result = localTransactionRepositoryImpl.getByID(1L)

        //Assert
        assertNotNull(result.exceptionOrNull())
    }

    private fun mockUser(isValid: Boolean) {
        val user = if (isValid) User(1L, "email@email.com", "password") else null
        UserManager.loggedUser = user
    }

    private fun getMockedLocalTransactionList() = mutableListOf<LocalTransaction>().apply {
        repeat(5) {
            add(
                getMockedLocalTransaction()
            )
        }
    }

    private fun getMockedLocalTransaction() = LocalTransaction(
        1L,
        1L,
        "title",
        TransactionCategory.STOCKS,
        1.0,
        1L,
        TransactionType.LOSS,
        Date().time
    )
}


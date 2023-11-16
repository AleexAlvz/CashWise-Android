package com.aleexalvz.cashwise.helper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CurrencyHelperKtTest {

    @Test
    fun `when convert zero then return right currency value`() {
        //Arrange
        val zero = 0.0
        val expectedCurrency = "R$ 0,00"

        //Act
        val actualCurrency = zero.toCurrencyString()

        //Assert
        assertEquals(expectedCurrency, actualCurrency)
    }
}
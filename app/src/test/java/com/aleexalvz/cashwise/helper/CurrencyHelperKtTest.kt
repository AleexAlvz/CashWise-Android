package com.aleexalvz.cashwise.helper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CurrencyHelperKtTest {

    @Test
    fun `when convert zero then return right currency value`() {
        //Arrange
        val value = 0.0
        val expected = "R$ 0,00"

        //Act
        val actualValue = value.toCurrencyString()

        //Assert
        assertEquals(expected, actualValue)
    }

    @Test
    fun `when convert value then return right value validating that is rounded to down`(){
        //Arrange
        val value = 15.159
        val expected = "R$ 15,15"

        //Act
        val actualValue = value.toCurrencyString()

        //Assert
        assertEquals(expected, actualValue)
    }

    @Test
    fun `when convert large value then return right value validating format`(){
        //Arrange
        val value = 151591591.15
        val expected = "R$ 151.591.591,15"

        //Act
        val actualValue = value.toCurrencyString()

        //Assert
        assertEquals(expected, actualValue)
    }

    @Test
    fun `when convert positive value then return right currency value`() {
        //Arrange
        val value = 1.57
        val expected = "R$ 1,57"

        //Act
        val actualValue = value.toCurrencyString()

        //Assert
        assertEquals(expected, actualValue)
    }

    @Test
    fun `when convert negative value then return right currency value with negative symbol before that`() {
        //Arrange
        val value = -1.57
        val expected = "-R$ 1,57"

        //Act
        val actualValue = value.toCurrencyString()

        //Assert
        assertEquals(expected, actualValue)
    }


}

package com.aleexalvz.cashwise.helper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DateHelperKtTest {
    @Test
    fun testToBrazilianDateFormat() {
        // Test case 1: Default pattern
        val result1 = 1637073654000L.toBrazilianDateFormat()
        assertEquals("16/11/2021", result1)

        // Test case 2: Custom pattern
        val result2 = 1637073654000L.toBrazilianDateFormat("yyyy-MM-dd")
        assertEquals("2021-11-16", result2)

        // Test case 3: Different timestamp
        val result3 = 1609459200000L.toBrazilianDateFormat()
        assertEquals("01/01/2021", result3)

        // Test case 4: Pattern with time
        val result4 = 1637073654000L.toBrazilianDateFormat("dd/MM/yyyy HH:mm:ss")
        assertEquals("16/11/2021 14:40:54", result4)

        // Test case 5: Negative timestamp
        val result5 = (-1637073654000L).toBrazilianDateFormat()
        assertEquals("15/02/1918", result5)

        // Test case 5: Zero timestamp
        val result6 = 0L.toBrazilianDateFormat()
        assertEquals("01/01/1970", result6)
    }
}
package com.aleexalvz.cashwise.data.source.local.typeconverter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Date

class DateConverterTest {

    private val dateConverter = DateConverter()

    @Test
    fun `test DateConverter methods`() {
        ///Arrange
        hashMapOf(
            1638224400000L to Date(1638224400000L),
            1638224400L to Date(1638224400L),
            38224400000L to Date(38224400000L),
            163800000L to Date(163800000L)
        ).forEach { (expectedTimestamp, expectedDate) ->
            //Act
            val date = dateConverter.fromTimestampToDate(expectedTimestamp)
            val timestamp = dateConverter.fromDateToTimestamp(date)

            //Assert
            assertEquals(expectedDate, date)
            assertEquals(expectedTimestamp, timestamp)
        }
    }
}
package com.aleexalvz.cashwise.data.source.local.typeconverter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimestampToDate(value: Long): Date = Date(value)

    @TypeConverter
    fun fromDateToTimestamp(value: Date): Long = value.time
}
package com.skytoph.books.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.skytoph.books.core.util.ConvertDate
import java.util.Date

@ProvidedTypeConverter
class DateConverter(
    private val converter: ConvertDate
) {

    @TypeConverter
    fun fromTimestamp(value: String?): Date? = value?.let { converter.stringToDate(value) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? = date?.let { converter.dateToString(date) }
}


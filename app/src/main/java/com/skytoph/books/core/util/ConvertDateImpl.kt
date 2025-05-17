package com.skytoph.books.core.util

import com.skytoph.books.core.resources.DateFormatProvider
import com.skytoph.books.core.resources.LocaleProvider
import java.text.SimpleDateFormat
import java.util.Date

class ConvertDateImpl(
    private val formatProvider: DateFormatProvider,
    private val localeProvider: LocaleProvider
) : ConvertDate {

    override fun stringToDate(value: String): Date? =
        SimpleDateFormat(formatProvider.datePattern, localeProvider.locale()).parse(value)

    override fun dateToString(date: Date): String =
        SimpleDateFormat(formatProvider.datePattern, localeProvider.locale()).format(date)
}
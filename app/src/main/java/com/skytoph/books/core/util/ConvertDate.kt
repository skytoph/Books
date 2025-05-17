package com.skytoph.books.core.util

import java.util.Date

interface ConvertDate {
    fun stringToDate(value: String): Date?
    fun dateToString(date: Date): String
}
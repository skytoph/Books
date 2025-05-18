package com.skytoph.books.core.resources

import android.content.res.Resources
import com.skytoph.books.R

class DateFormatIso(resources: Resources) : DateFormatProvider {

    override val datePattern: String = resources.getString(R.string.pattern_date)
}

class DateFormatDayMonth(resources: Resources) : DateFormatProvider {

    override val datePattern: String = resources.getString(R.string.pattern_day_month)
}
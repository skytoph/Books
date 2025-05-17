package com.skytoph.books.core.resources

import android.content.res.Resources
import com.skytoph.books.R

class DateFormatProviderImpl(resources: Resources) : DateFormatProvider {

    override val datePattern: String = resources.getString(R.string.pattern_date)
}
package com.nihilus13.data.date

import java.util.Calendar
import javax.inject.Inject

internal class DateSourceImpl @Inject constructor(private val calendar: Calendar) :
    DateSource {

    override fun getCurrentDateTimestamp(): Long = calendar.timeInMillis
}

package com.max.kkday.model

import java.util.*

object CalendarUtil {

    private val GMT_PLUS_EIGHT_TIME_ZONE: TimeZone = TimeZone.getTimeZone("GMT+8")

    /**
     * 取得Taiwan時區的時間
     *
     * @return Calendar
     */
    fun getTaiwanTime(): Calendar {
        return Calendar.getInstance(GMT_PLUS_EIGHT_TIME_ZONE)
    }

    /**
     * 取得 [timeInMillis] 在Taiwan時區的時間
     */
    fun getTaiwanTime(timeInMillis: Long): Calendar {
        val taiwanTime = getTaiwanTime()
        taiwanTime.timeInMillis = timeInMillis
        return taiwanTime
    }
}

package com.justclean.core.heplers

import org.joda.time.DateTime
import java.util.*

interface DateTimeHelper {

    fun getHumanDateTime(newFormat: String, dateTime: String): String

    fun getHumanDateTime(oldFormat: String, newFormat: String, dateTime: String): String

    fun getNormalMonth(date: String?): String

    fun getNormalDate(date: String?): String

    fun getDateTimeFromDate(date: String): DateTime

    fun printDateTime(date: DateTime): String

    fun isToday(date: Date): Boolean

    fun isTomorrow(date: Date): Boolean


    companion object {
        const val ORIGINAL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val NORMAL_DATE_FORMAT = "yyyy-MM-dd"
        const val NORMAL_MONTH_FORMAT = "yyyy-MM"
        const val MIX_PANEL_DATE_FORMAT = "yyyy-MM-dd"

        const val MIX_PANEL_DATE_FORMAT_WITH_TIME = "yyyy-MM-dd hh:mm a"
        const val TRANSACTION_DATE_FORMAT_WITH_TIME = "EEEE dd/MM/yyyy hh:mm a"

        const val MIX_PANEL_TIME = "HH:mm"
        const val NORMAL_DOTES_DATE_FORMAT = "yyyy.MM.dd"
        const val NORMAL_TIME_FORMAT = "hh:mm a"
        const val NORMAL_DAY = "EEEE"
        const val NORMAL_DAY_SHORT = "E"
        const val NORMAL_CHAR_DAY = "E"
        const val HUMAN_DATE = "EEEE dd.MMM"
        const val SIMPLE_TIME = "h a"
        const val SIMPLE_DATE = "MMM d"
        const val FILTER_DATE = "MMMM d"
        const val OLD_FORMAT = "HH:mm:ss"
        const val SIMPLE_TIME_H_M = "HH:mm"
        const val NORMAL_DAY_DATE = "EEEE d"
        const val NORMAL_DAY_DATE_SHORT = "E d"
        const val SIMPLE_TIME_H = "h"
        const val NORMAL_TIME_H_FORMAT = "h a"
        const val SIMPLE_MONTH_DATE = "MMM, yyyy"
        const val SIMPLE_DAY_NAME_MONTH_DAY = "EEEE, MMMM d"
        const val SIMPLE_DATE_WITH_SPACE = "dd MMM yyyy"
    }
}

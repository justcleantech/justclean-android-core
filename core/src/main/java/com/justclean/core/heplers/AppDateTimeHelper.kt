package com.justclean.core.heplers

import android.annotation.SuppressLint
import android.text.format.DateUtils
import com.akexorcist.localizationactivity.core.LanguageSetting
import com.justclean.core.heplers.DateTimeHelper.Companion.ORIGINAL_DATE_TIME_FORMAT
import com.justclean.core.heplers.DateTimeHelper.Companion.SIMPLE_DATE_WITH_SPACE
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.util.*


object AppDateTimeHelper : DateTimeHelper {


    override fun getHumanDateTime(oldFormat: String, newFormat: String, dateTime: String): String {
        val newDate = DateTime.parse(dateTime, DateTimeFormat.forPattern(oldFormat))
        return newDate.toString(newFormat, Locale(LanguageSetting.getDefaultLanguage().language))
    }


    override fun getHumanDateTime(newFormat: String, dateTime: String): String {
        val newDate = DateTime.parse(dateTime, DateTimeFormat.forPattern(ORIGINAL_DATE_TIME_FORMAT).withZoneUTC())
        val parsed = newDate.toDateTime(DateTimeZone.forTimeZone(TimeZone.getDefault())).toDateTime()
        return parsed.toString(newFormat, Locale(LanguageSetting.getDefaultLanguage().language))
    }

    override fun getDateTimeFromDate(date: String): DateTime {
        return DateTime.parse(date, DateTimeFormat.forPattern(ORIGINAL_DATE_TIME_FORMAT).withZoneUTC())
    }

    override fun printDateTime(date: DateTime): String {
        return DateTimeFormat.forPattern(DateTimeHelper.ORIGINAL_DATE_TIME_FORMAT).print(date)
    }

    override fun isToday(date: Date): Boolean {
        return DateUtils.isToday(date.time)
    }

    override fun isTomorrow(date: Date): Boolean {
        return DateUtils.isToday(date.time - DateUtils.DAY_IN_MILLIS)
    }

    fun getStartEndDate(start: String, end: String): String {
        return "${getHumanDateTime(
            DateTimeHelper.NORMAL_TIME_FORMAT,
            start
        )} - ${getHumanDateTime(
            DateTimeHelper.NORMAL_TIME_FORMAT,
            end
        )}"
    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToDate(time: Long): String {
        val date = Date(time * 1000L)
        val format = SimpleDateFormat(SIMPLE_DATE_WITH_SPACE, Locale(LanguageSetting.getDefaultLanguage().language))
        return format.format(date)
    }

    fun parseDate(date: String): Date {

        val inputFormat = "HH:mm"
        val inputParser = SimpleDateFormat(inputFormat, Locale(LanguageSetting.getDefaultLanguage().language))
        try {
            return inputParser.parse(date)
        } catch (e: java.text.ParseException) {
            return Date(0)
        }

    }

    fun getDayMonth(date: String): String {
        var orgFormat = SimpleDateFormat(ORIGINAL_DATE_TIME_FORMAT)
        val newDate = orgFormat.parse(date)
        orgFormat = SimpleDateFormat("MMM d")
        return orgFormat.format(newDate)
    }

    fun getStartEndTime(startDate: String, endDate: String): String {
        var orgFormat = SimpleDateFormat(ORIGINAL_DATE_TIME_FORMAT)
        val startTime = orgFormat.parse(startDate)
        val endTime = orgFormat.parse(endDate)
        orgFormat = SimpleDateFormat("hh:mm")
        return orgFormat.format(startTime) + " - " + orgFormat.format(endTime)
    }

    fun getStartEndTimeWithTT(startDate: String, endDate: String): String {
        var orgFormat = SimpleDateFormat(ORIGINAL_DATE_TIME_FORMAT)
        val startTime = orgFormat.parse(startDate)
        val endTime = orgFormat.parse(endDate)
        orgFormat = SimpleDateFormat("hh:mm tt")
        return orgFormat.format(startTime) + " - " + orgFormat.format(endTime)
    }

    override fun getNormalMonth(date: String?): String {
        return if (date != null) getHumanDateTime(
            DateTimeHelper.NORMAL_MONTH_FORMAT,
            date
        )
        else ""
    }

    override fun getNormalDate(date: String?): String {
        return if (date != null) getHumanDateTime(
            DateTimeHelper.NORMAL_DATE_FORMAT,
            date
        )
        else ""
    }

}

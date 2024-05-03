package com.kotlin.api.Utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeUtility {

    companion object {
        private const val DATE_FORMAT = "dd-MM-yyyy"
        private const val DATE_FORMAT_SECONDARY = "dd-MM-yyyy"
        private const val DATE_FORMAT_WITH_TIME = "dd-MM-yyyy HH:mm"
        private const val DATE_FORMAT_WITH_TIME_WITH_NO_SPACE = "ddMMyyyyHHmmss"
        private const val ENACH_DATE_FORMAT = "dd-MM-yyyy"
        private const val ENACH_SCHEDULE_DATE_FORMAT = "ddMMyyyy"
        private const val LOAN_AGREEMENT_DATE_FORMAT = "dd/MM/yyyy"
        private const val DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd"
        private const val DATE_FORMAT_FILTER = "yyyy-MM-dd"
        private const val DATE_FORMAT_YYYY_MM_DD_WITH_TIME = "yyyy-MM-dd HH:mm:ss"
        private const val TEA_GARDEN_DATE_FORMAT = "MM/dd/yyyy"
        fun getDateToStrYMDWithoutTimeStamp(date : Date) : String{
            val sdf = SimpleDateFormat(DATE_FORMAT)
            return sdf.format(date)
        }

        fun convertStrToDate(date: String?): Date? {
            val df = SimpleDateFormat(DATE_FORMAT)
            var dateTime: Date? = null
            try {
                dateTime = df.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return dateTime
        }

        fun convertStrToDateYMD(date: String?): Date? {
            val df = SimpleDateFormat(DATE_FORMAT_FILTER)
            var dateTime: Date? = null
            try {
                dateTime = df.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return dateTime
        }
    }
}
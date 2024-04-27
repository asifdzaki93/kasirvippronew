package id.kasirvippro.android.utils

import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by adriyoutomo on 29/07/2019.
 */
object DateUtil {

    fun getDate(day: Int, month: Int, year: Int): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, day)
        return cal.time
    }

    fun getString(date: Date, context: Context): String {
        val formatTo = "dd MMMM yyyy hh:mm:ss"
        val sdfAfter = SimpleDateFormat(formatTo, Helper.getIdLocale(context))
        //        SimpleDateFormat sdfAfter = new SimpleDateFormat(formatTo, Locale.getDefault());
        return sdfAfter.format(date)
    }

    @Throws(ParseException::class)
    fun getDateFormat(context: Context, date: String?, formatFrom: String, formatTo: String): String {
        if (date == null) {
            return "-"
        }
        val sdfBefore = SimpleDateFormat(formatFrom, Helper.getIdLocale(context))
        val dateBefore = sdfBefore.parse(date)

        val sdfAfter = SimpleDateFormat(formatTo, Helper.getIdLocale(context))
        return sdfAfter.format(dateBefore)
    }

    fun getStringAsDate(context: Context, date: String?): Date {
        if (date == null) {
            return Date()
        }
        val format = SimpleDateFormat("yyyy-MM-dd", Helper.getIdLocale(context))
        return format.parse(date) ?: Date()
    }
}
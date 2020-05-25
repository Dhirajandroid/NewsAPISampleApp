package com.newsapi.sampleapp.utils

import android.text.format.DateUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtilityMethods {
    fun formatDate(dateStringUTC: String?): String { // Parse the dateString into a Date object
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'")
        var dateObject: Date? = null
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        // Initialize a SimpleDateFormat instance and configure it to provide a more readable
// representation according to the given format, but still in UTC
        val df = SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH)
        val formattedDateUTC = df.format(dateObject)
        // Convert UTC into Local time
        df.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = null
        try {
            date = df.parse(formattedDateUTC)
            df.timeZone = TimeZone.getDefault()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return df.format(date)
    }

    /**
     * Get the formatted web publication date string in milliseconds
     * @param formattedDate the formatted web publication date string
     * @return the formatted web publication date in milliseconds
     */
    private fun getDateInMillis(formattedDate: String): Long {
        val simpleDateFormat = SimpleDateFormat("MMM d, yyyy  h:mm a")
        val dateInMillis: Long
        val dateObject: Date
        try {
            dateObject = simpleDateFormat.parse(formattedDate)
            dateInMillis = dateObject.time
            return dateInMillis
        } catch (e: ParseException) {
            Log.e("Problem parsing date", e.message)
            e.printStackTrace()
        }
        return 0
    }

    /**
     * Get the time difference between the current date and web publication date
     * @param formattedDate the formatted web publication date string
     * @return time difference (i.e "9 hours ago")
     */
    fun getTimeDifference(formattedDate: String): CharSequence {
        val currentTime = System.currentTimeMillis()
        val publicationTime = getDateInMillis(formattedDate)
        return DateUtils.getRelativeTimeSpanString(publicationTime, currentTime,
                DateUtils.SECOND_IN_MILLIS)
    }
}
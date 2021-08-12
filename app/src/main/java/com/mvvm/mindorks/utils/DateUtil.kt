package com.mvvm.mindorks.utils

object DateUtil {

    fun getFormattedDate(date : String?) : String{
        if (date.isNullOrEmpty()) return ""
        return date.split("T")[0]
    }

}
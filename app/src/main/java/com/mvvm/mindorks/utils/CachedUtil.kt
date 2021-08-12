package com.mvvm.mindorks.utils

object CachedUtil {

    fun valid(cachedAt: Long?): Boolean {
        if (cachedAt == null) return false
        val fiveMinute = 5 * 60 * 1000
        return System.currentTimeMillis() - cachedAt < fiveMinute
    }

}
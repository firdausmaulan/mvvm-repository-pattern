package com.mvvm.mindorks.utils

import android.util.Log

data class State<out T>(val status: Status?, val throwable: Throwable?, val data: T?) {

    companion object {

        fun <T> success(data: T?): State<T> {
            return State(Status.SUCCESS, null, data)
        }

        fun <T> error(throwable: Throwable?, data: T? = null): State<T> {
            Log.e("ThrowableResource", throwable?.message.toString())
            return State(Status.ERROR, throwable, data)
        }

        fun <T> loading(data: T? = null): State<T> {
            return State(Status.LOADING, null, data)
        }

    }

}
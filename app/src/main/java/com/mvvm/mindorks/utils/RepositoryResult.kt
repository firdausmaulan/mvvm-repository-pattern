package com.mvvm.mindorks.utils

import android.util.Log

data class RepositoryResult<out T>(val status: Status?, val throwable: Throwable?, val data: T?) {

    companion object {

        fun <T> success(data: T?): RepositoryResult<T> {
            return RepositoryResult(Status.SUCCESS, null, data)
        }

        fun <T> error(throwable: Throwable?, data: T? = null): RepositoryResult<T> {
            Log.e("ThrowableResource", throwable?.message.toString())
            return RepositoryResult(Status.ERROR, throwable, data)
        }

    }

}
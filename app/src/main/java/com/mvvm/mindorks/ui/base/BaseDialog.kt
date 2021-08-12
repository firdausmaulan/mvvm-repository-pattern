package com.mvvm.mindorks.ui.base

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mvvm.mindorks.R
import com.mvvm.mindorks.utils.StatusCode
import retrofit2.HttpException

class BaseDialog {

    fun show(view: View, throwable: Throwable?) {
        val errorMessage = getErrorMessage(view, throwable)
        val statusCode = getStatusCode(throwable)
        if (statusCode == StatusCode.SUCCESS) {
            showSuccessSnackBar(view, errorMessage)
        } else {
            showErrorSnackBar(view, errorMessage)
        }
    }

    private fun getErrorMessage(view: View, throwable: Throwable?): String {
        var errorMessage = view.context.getString(R.string.network_error_message)
        if (throwable != null && throwable is HttpException) {
            errorMessage = throwable.message().toString()
        }
        return errorMessage
    }

    private fun getStatusCode(throwable: Throwable?): Int {
        if (throwable != null && throwable is HttpException) {
            return throwable.code()
        }
        return StatusCode.NETWORK_ERROR
    }

    private fun showSuccessSnackBar(view: View, errorMessage: String) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.GREEN)
            .setTextColor(Color.WHITE)
            .show()
    }

    private fun showErrorSnackBar(view: View, errorMessage: String) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.RED)
            .setTextColor(Color.WHITE)
            .show()
    }
}
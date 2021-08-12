package com.mvvm.mindorks.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView

object KeyboardUtil {
    fun hide(view: View?) {
        val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun show(view: View?) {
        val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun hideOnScroll(recyclerView: RecyclerView?) {
        recyclerView?.setOnTouchListener { _, _ ->
            hide(recyclerView)
            false
        }
    }
}
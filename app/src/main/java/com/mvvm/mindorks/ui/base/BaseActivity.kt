package com.mvvm.mindorks.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

// https://pratamawijaya.com/android/kotlin-android-extensions-deprecated/
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> T

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    protected val baseDialog = BaseDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setupData()
        setupViewModel()
        setupView(binding)
        setupObserver()
    }

    protected open fun setupData() {

    }

    protected open fun setupViewModel() {

    }

    abstract fun setupView(binding: T)

    protected open fun setupObserver() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
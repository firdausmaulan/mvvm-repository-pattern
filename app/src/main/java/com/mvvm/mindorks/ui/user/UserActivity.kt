package com.mvvm.mindorks.ui.user

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mvvm.mindorks.data.local.AppDatabase
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.model.presentation.User
import com.mvvm.mindorks.data.model.request.UserRequest
import com.mvvm.mindorks.data.remote.ApiHelper
import com.mvvm.mindorks.databinding.UserActivityBinding
import com.mvvm.mindorks.ui.base.BaseActivity
import com.mvvm.mindorks.utils.Status

class UserActivity : BaseActivity<UserActivityBinding>() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var username: String
    private var user: User? = null

    override val bindingInflater: (LayoutInflater) -> UserActivityBinding
        get() = UserActivityBinding::inflate

    companion object {

        private const val USERNAME = "username"

        fun startIntent(activity: Activity, username: String): Intent {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra(USERNAME, username)
            return intent
        }
    }

    override fun setupData() {
        username = intent.getStringExtra(USERNAME).toString()
    }

    override fun setupViewModel() {
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(
                ApiHelper(),
                AppDatabase.Builder().build(applicationContext),
                UserMapper()
            )
        ).get(UserViewModel::class.java)

        userViewModel.fetchUser(UserRequest(username))
    }

    override fun setupView(binding: UserActivityBinding) {
        user?.let {
            Glide.with(this).load(it.avatarUrl).into(binding.ivAvatar)
            binding.tvName.text = it.name
            binding.tvBio.text = it.bio
            binding.tvLocation.text = it.location
            binding.tvCrated.text = it.createdAt
        }
    }

    override fun setupObserver() {
        userViewModel.userLiveData.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    user = it.data
                    setupView(binding)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    baseDialog.show(binding.container, it.throwable)
                }
            }
        })
    }
}
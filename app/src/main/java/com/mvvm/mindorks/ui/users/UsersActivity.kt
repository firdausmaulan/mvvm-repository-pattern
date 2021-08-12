package com.mvvm.mindorks.ui.users

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.mindorks.data.local.AppDatabase
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.model.presentation.User
import com.mvvm.mindorks.data.model.request.UsersRequest
import com.mvvm.mindorks.data.remote.ApiHelper
import com.mvvm.mindorks.databinding.UsersActivityBinding
import com.mvvm.mindorks.ui.base.BaseActivity
import com.mvvm.mindorks.ui.user.UserActivity
import com.mvvm.mindorks.utils.Constant
import com.mvvm.mindorks.utils.KeyboardUtil
import com.mvvm.mindorks.utils.Status

class UsersActivity : BaseActivity<UsersActivityBinding>() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var adapter: UserAdapter

    override val bindingInflater: (LayoutInflater) -> UsersActivityBinding
        get() = UsersActivityBinding::inflate

    override fun setupViewModel() {
        usersViewModel = ViewModelProvider(
            this,
            UsersViewModelFactory(
                ApiHelper(),
                AppDatabase.Builder().build(applicationContext),
                UserMapper()
            )
        ).get(UsersViewModel::class.java)
    }

    override fun setupView(binding: UsersActivityBinding) {
        binding.btnSearch.setOnClickListener {
            KeyboardUtil.hide(binding.btnSearch)
            val query = binding.etSearch.text.toString()
            val request = UsersRequest(query, 1, Constant.PER_PAGE)
            usersViewModel.fetchUsers(request)
        }

        adapter = UserAdapter(arrayListOf())
        binding.rvUser.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
        binding.rvUser.adapter = adapter

        adapter.setListener(object : UserAdapter.Listener {
            override fun onClick(user: User) {
                val intent = UserActivity.startIntent(this@UsersActivity, user.login)
                startActivity(intent)
            }
        })
    }

    override fun setupObserver() {
        usersViewModel.usersLiveData.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvUser.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.rvUser.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    baseDialog.show(binding.container, it.throwable)
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.clear()
        adapter.addAll(users)
    }
}
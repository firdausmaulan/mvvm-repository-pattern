package com.mvvm.mindorks.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.mindorks.data.local.AppDatabase
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.remote.ApiHelper
import com.mvvm.mindorks.data.repository.user.UserRepositoryImp

class UsersViewModelFactory(
    private val apiHelper: ApiHelper,
    private val appDatabase: AppDatabase,
    private val userMapper: UserMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(
                UserRepositoryImp(apiHelper.apiService, appDatabase, userMapper),
                userMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
package com.mvvm.mindorks.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.mindorks.data.local.AppDatabase
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.remote.ApiHelper
import com.mvvm.mindorks.data.repository.user.UserRepositoryImp

class UserViewModelFactory(
    private val apiHelper: ApiHelper,
    private val appDatabase: AppDatabase,
    private val userMapper: UserMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                UserRepositoryImp(apiHelper.apiService, appDatabase, userMapper),
                userMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
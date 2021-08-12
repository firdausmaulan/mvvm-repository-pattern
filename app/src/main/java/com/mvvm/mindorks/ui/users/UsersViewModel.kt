package com.mvvm.mindorks.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.model.presentation.User
import com.mvvm.mindorks.data.model.request.UsersRequest
import com.mvvm.mindorks.data.repository.user.UserRepository
import com.mvvm.mindorks.utils.State
import com.mvvm.mindorks.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModel() {

    val usersLiveData = MutableLiveData<State<List<User>>>()

    fun fetchUsers(request: UsersRequest) {
        viewModelScope.launch {
            usersLiveData.postValue(State.loading())
            val result = withContext(Dispatchers.IO) { userRepository.getUsers(request) }
            if (result.status == Status.SUCCESS) {
                val usersPresentation = userMapper.mapToPresentation(result.data)
                usersLiveData.postValue(State.success(usersPresentation))
            } else {
                usersLiveData.postValue(State.error(result.throwable))
            }
        }
    }

}
package com.mvvm.mindorks.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.model.presentation.User
import com.mvvm.mindorks.data.model.request.UserRequest
import com.mvvm.mindorks.data.repository.user.UserRepository
import com.mvvm.mindorks.utils.State
import com.mvvm.mindorks.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModel() {

    val userLiveData = MutableLiveData<State<User>>()

    fun fetchUser(request: UserRequest) {
        viewModelScope.launch {
            userLiveData.postValue(State.loading())
            val result = withContext(Dispatchers.IO) { userRepository.getUser(request) }
            if (result.status == Status.SUCCESS) {
                val userPresentation = userMapper.mapToPresentation(result.data)
                userLiveData.postValue(State.success(userPresentation))
            } else {
                userLiveData.postValue(State.error(result.throwable))
            }
        }
    }

}
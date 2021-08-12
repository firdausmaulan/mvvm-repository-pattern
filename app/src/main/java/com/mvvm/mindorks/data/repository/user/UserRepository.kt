package com.mvvm.mindorks.data.repository.user

import com.mvvm.mindorks.data.model.entity.UserEntity
import com.mvvm.mindorks.data.model.request.UserRequest
import com.mvvm.mindorks.data.model.request.UsersRequest
import com.mvvm.mindorks.utils.RepositoryResult

interface UserRepository {

    suspend fun getUsers(request: UsersRequest): RepositoryResult<List<UserEntity>>

    suspend fun getUser(request: UserRequest): RepositoryResult<UserEntity>

}
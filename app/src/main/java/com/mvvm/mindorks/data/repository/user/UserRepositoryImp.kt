package com.mvvm.mindorks.data.repository.user

import com.mvvm.mindorks.data.local.AppDatabase
import com.mvvm.mindorks.data.model.entity.UserEntity
import com.mvvm.mindorks.data.model.mapper.UserMapper
import com.mvvm.mindorks.data.model.request.UserRequest
import com.mvvm.mindorks.data.model.request.UsersRequest
import com.mvvm.mindorks.data.remote.ApiService
import com.mvvm.mindorks.utils.CachedUtil
import com.mvvm.mindorks.utils.RepositoryResult

class UserRepositoryImp constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUsers(request: UsersRequest): RepositoryResult<List<UserEntity>> {
        return try {
            val response = apiService.getUsers(request.query, request.page, request.perPage)
            RepositoryResult.success(userMapper.mapToEntity(response.items))
        } catch (throwable: Throwable) {
            RepositoryResult.error(throwable)
        }
    }

    override suspend fun getUser(request: UserRequest): RepositoryResult<UserEntity> {
        var result: UserEntity? = null
        return try {
            result = appDatabase.useDao().getByUsername(request.username)
            if (CachedUtil.valid(result?.cachedAt)) {
                RepositoryResult.success(result)
            } else {
                val response = apiService.getUser(request.username)
                result = userMapper.mapToEntity(response)
                appDatabase.useDao().insert(result)
                RepositoryResult.success(result)
            }
        } catch (throwable: Throwable) {
            if (result?.cachedAt != null) {
                RepositoryResult.success(result)
            } else {
                RepositoryResult.error(throwable)
            }
        }
    }
}
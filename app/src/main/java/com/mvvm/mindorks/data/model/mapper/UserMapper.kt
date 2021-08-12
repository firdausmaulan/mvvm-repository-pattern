package com.mvvm.mindorks.data.model.mapper

import com.mvvm.mindorks.data.model.entity.UserEntity
import com.mvvm.mindorks.data.model.presentation.User
import com.mvvm.mindorks.data.model.response.Item
import com.mvvm.mindorks.data.model.response.UserResponse
import com.mvvm.mindorks.utils.DateUtil

// I need mapper for mapping data from server or db to presentation model
// Some times data need to be formatted on presentation model ex : date or progress in percent

class UserMapper {

    fun mapToEntity(user: UserResponse): UserEntity {
        return UserEntity(
            id = user.id,
            login = user.login,
            avatarUrl = user.avatarUrl,
            htmlUrl = user.htmlUrl,
            name = user.name,
            location = user.location,
            bio = user.bio,
            createdAt = user.createdAt,
            cachedAt = System.currentTimeMillis()
        )
    }

    private fun mapToEntity(item: Item): UserEntity {
        return UserEntity(
            id = item.id,
            login = item.login,
            avatarUrl = item.avatarUrl,
            htmlUrl = item.htmlUrl
        )
    }

    fun mapToEntity(items: List<Item>?): List<UserEntity> {
        if (items.isNullOrEmpty()) return emptyList()
        return items.map { mapToEntity(it) }
    }

    fun mapToPresentation(entity: UserEntity?): User {
        return User(
            id = entity?.id ?: 0,
            login = entity?.login ?: "",
            avatarUrl = entity?.avatarUrl ?: "",
            htmlUrl = entity?.htmlUrl ?: "",
            name = entity?.name,
            location = entity?.location,
            bio = entity?.bio,
            createdAt = DateUtil.getFormattedDate(entity?.createdAt)
        )
    }

    fun mapToPresentation(users: List<UserEntity>?): List<User> {
        if (users.isNullOrEmpty()) return emptyList()
        return users.map { mapToPresentation(it) }
    }
}
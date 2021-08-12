package com.mvvm.mindorks.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_user")
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "login")
    val login: String? = null,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,

    @ColumnInfo(name = "html_url")
    val htmlUrl: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "location")
    val location: String? = null,

    @ColumnInfo(name = "bio")
    val bio: String? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: String? = null,

    @ColumnInfo(name = "cached_at")
    val cachedAt: Long? = null
)
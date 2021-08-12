package com.mvvm.mindorks.data.model.presentation

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("login")
    val login: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("bio")
    val bio: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = ""
)
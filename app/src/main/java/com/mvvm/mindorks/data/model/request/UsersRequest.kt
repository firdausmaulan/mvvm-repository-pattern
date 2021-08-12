package com.mvvm.mindorks.data.model.request

data class UsersRequest(
    val query: String = "",
    val page: Int = 1,
    val perPage: Int = 20
)
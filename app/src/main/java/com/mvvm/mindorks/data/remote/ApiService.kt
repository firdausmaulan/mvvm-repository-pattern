package com.mvvm.mindorks.data.remote

import com.mvvm.mindorks.data.model.response.UserResponse
import com.mvvm.mindorks.data.model.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") q: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): UsersResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String?
    ): UserResponse

}
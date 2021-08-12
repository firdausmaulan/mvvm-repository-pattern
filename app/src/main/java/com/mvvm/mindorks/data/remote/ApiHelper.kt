package com.mvvm.mindorks.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {
    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())

        val client = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(httpLoggingInterceptor)

        client.addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build()
            chain.proceed(newRequest)
        }

        retrofit.client(client.build()).build().create(ApiService::class.java)
    }
}
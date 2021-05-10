package com.example.appercodetest.respository

import com.example.appercodetest.model.User
import com.example.appercodetest.model.UserDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class Repository @Inject constructor() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val service by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun getUsers(since: Int, pageSize: Int): Response<ArrayList<User>> {
        return service.getUsers(since, pageSize)
    }

    suspend fun getUserDetail(url: String): Response<UserDetail> {
        return service.getUserDetail(url)
    }


}
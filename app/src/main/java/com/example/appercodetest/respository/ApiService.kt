package com.example.appercodetest.respository

import com.example.appercodetest.model.User
import com.example.appercodetest.model.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query ("since") since: Int,
        @Query("per_page") perPage: Int
    ): Response<ArrayList<User>>

    @GET()
    suspend fun getUserDetail(
        @Url url: String
    ): Response<UserDetail>
}
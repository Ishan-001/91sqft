package com.example.radius.data.network.service

import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("d4c44d51-436c-4c19-b306-908ab1ec0ca2")
    suspend fun getFacilities(): Response<>
}
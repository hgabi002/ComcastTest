package com.example.comcasttest.model

import com.example.comcasttest.BuildConfig
import retrofit2.Response
import retrofit2.http.GET


interface DuckDuckApi {
    @GET(BuildConfig.END_POINT)
    suspend fun getResponse(): Response<com.example.comcasttest.model.Response>
}
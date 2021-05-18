package com.example.comcasttest.model

import com.example.comcasttest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {
    companion object {
        private var INSTANCE: Network? = null

        fun getInstance(): Network {
            synchronized(this) {
                var temp = INSTANCE
                if (temp != null)
                    return temp

                temp = Network()
                INSTANCE = temp
                return temp
            }
        }
    }

    fun getApi(): DuckDuckApi{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DuckDuckApi::class.java)
    }
}
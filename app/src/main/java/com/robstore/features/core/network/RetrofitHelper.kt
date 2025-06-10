package com.robstore.features.core.network

import com.robstore.features.authentication.data.datasource.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitHelper {
    private const val BASE_URL = "https://store.eduartrob.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }
}



package com.example.apppatitasidatsjm.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object PatitasCliente {

    private var retrofitCliente = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        //.addInterceptor()
        .build()
    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://www.kreapps.biz/patitas/")
        .client(retrofitCliente)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: PatitasServicio by lazy {
        buildRetrofit().create(PatitasServicio::class.java)
    }

}
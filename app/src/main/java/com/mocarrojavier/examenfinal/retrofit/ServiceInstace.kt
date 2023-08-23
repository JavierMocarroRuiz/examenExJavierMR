package com.mocarrojavier.examenfinal.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceInstace {

    //https://www.cheapshark.com/api/1.0/games?title=batman

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.cheapshark.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

    val gameService: Service = retrofit.create(Service::class.java)

}
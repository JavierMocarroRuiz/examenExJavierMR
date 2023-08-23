package com.mocarrojavier.examenfinal.retrofit

import com.mocarrojavier.examenfinal.response.ListResponse
import retrofit2.http.GET

interface Service {

    @GET("1.0/games?title=batman")
    suspend fun getGames() : ListResponse

    }

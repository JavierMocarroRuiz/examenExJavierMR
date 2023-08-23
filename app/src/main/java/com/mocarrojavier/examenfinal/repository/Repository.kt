package com.mocarrojavier.examenfinal.repository

import com.mocarrojavier.examenfinal.db.Dao
import com.mocarrojavier.examenfinal.model.Games
import com.mocarrojavier.examenfinal.response.ApiResponse
import com.mocarrojavier.examenfinal.response.ListResponse
import com.mocarrojavier.examenfinal.retrofit.ServiceInstace
import java.lang.Exception

class Repository(val dao: Dao?= null)  {

    suspend fun getGames(): ApiResponse<ListResponse> {
        return try{
            val response = ServiceInstace.gameService.getGames()
            ApiResponse.Success(response)
        } catch (e: Exception){
            ApiResponse.Error(e)
        }
    }

    //class MovieRepository(private val apiKey : String) {
    //val response = RetrofitHelper.movieService.getMoviesnowPlaying("63d5d0b9cad1af86d5ebb3651456acc6")

    suspend fun addFavorite(games: Games){
        dao?.let {
            it.addFavorite(games)
        }
    }

    fun getFavorites(): List<Games>{
        dao?.let {
            return it.getFavorites()
        } ?: kotlin.run {
            return listOf()
        }
    }


}
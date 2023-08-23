package com.mocarrojavier.examenfinal.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mocarrojavier.examenfinal.model.Game
import com.mocarrojavier.examenfinal.model.Games
import com.mocarrojavier.examenfinal.model.getData
import com.mocarrojavier.examenfinal.repository.Repository
import com.mocarrojavier.examenfinal.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    public var gameList: MutableLiveData<List<Games>> = MutableLiveData<List<Games>>()

    private val repository = Repository()

    val games : LiveData<List<Games>> = gameList

    fun getGamesList(){
        val data= getData()
        gameList.value = data
    }

    fun getGamesFromService(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getGames()
            when(response){
                is ApiResponse.Success -> {
                    gameList.postValue(response.data.games)
                }
                is ApiResponse.Error -> {

                }
            }

        }
    }

}
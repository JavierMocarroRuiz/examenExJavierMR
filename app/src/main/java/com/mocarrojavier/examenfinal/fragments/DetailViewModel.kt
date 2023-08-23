package com.mocarrojavier.examenfinal.fragments

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mocarrojavier.examenfinal.db.DataBase
import com.mocarrojavier.examenfinal.model.Games
import com.mocarrojavier.examenfinal.repository.Repository

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository

    init {
        val db = DataBase.getDatabase(application)
        repository = Repository(db.dao())
    }

    fun addFavorite(games: Games){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(games)
        }
    }

}
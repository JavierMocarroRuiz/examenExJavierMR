package com.mocarrojavier.examenfinal.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mocarrojavier.examenfinal.db.DataBase
import com.mocarrojavier.examenfinal.model.Games
import com.mocarrojavier.examenfinal.repository.Repository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository
    private val _favorites: MutableLiveData<List<Games>> = MutableLiveData()

    val favorites: LiveData<List<Games>> = _favorites

    init {
        val db = DataBase.getDatabase(application)
        repository = Repository(db.dao())
        getFavorites()
    }

    fun getFavorites(){
        val data = repository.getFavorites()

        _favorites.value = data
    }



}
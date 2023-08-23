package com.mocarrojavier.examenfinal.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mocarrojavier.examenfinal.model.Games


@Dao
interface Dao {

    @Query("SELECT * FROM games")
    fun getFavorites(): List<Games>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(games: Games)

}
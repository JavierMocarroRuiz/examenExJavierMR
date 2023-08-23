package com.mocarrojavier.examenfinal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mocarrojavier.examenfinal.model.Games


@Database(entities = [Games::class], version = 1)
abstract class DataBase: RoomDatabase()  {

    abstract fun dao(): Dao

    companion object{
        @Volatile
        private var instance: DataBase? = null
        fun getDatabase(context: Context): DataBase{
            if(instance==null){
                synchronized(this){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context): DataBase? {
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "games_database"
            ).build()
        }


    }

}
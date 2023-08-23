package com.mocarrojavier.examenfinal.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "games")
@Parcelize
data class Games(

    @PrimaryKey
    @SerializedName("gameID")
    val gameID: Int,
    @SerializedName("steamAppID")
    val steam: String,
    @SerializedName("cheapest")
    val cheapest: String,
    @SerializedName("cheapestDealID")
    val cheapestDealID: String,
    @SerializedName("external")
    val external: String,
    @SerializedName("internalName")
    val internalName: String,
    @SerializedName("thumb")
    val thumb: String,

): Parcelable

fun getData(): List<Games>{
    return listOf(
        Games(1,"","15.90","batman","Batman","Batman Lego",""),
        Games(2,"","16.90","batman","Batman","Batman Lego",""),
        Games(3,"","17.90","batman","Batman","Batman Lego",""),
        Games(4,"","18.90","batman","Batman","Batman Lego",""),
        Games(5,"","19.90","batman","Batman","Batman Lego",""),
        Games(6,"","26.90","batman","Batman","Batman Lego","")

    )
}

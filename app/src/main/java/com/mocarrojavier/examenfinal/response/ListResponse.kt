package com.mocarrojavier.examenfinal.response

import com.google.gson.annotations.SerializedName
import com.mocarrojavier.examenfinal.model.Games

data class ListResponse(
    val games: List<Games>

)

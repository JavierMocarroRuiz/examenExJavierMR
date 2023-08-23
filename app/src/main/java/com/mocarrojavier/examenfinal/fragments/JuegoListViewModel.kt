package com.mocarrojavier.examenfinal.fragments

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class JuegoListViewModel: ViewModel()  {

    private val firestore = FirebaseFirestore.getInstance()
    fun getJuegoList() = firestore.collection("lego").get()
}
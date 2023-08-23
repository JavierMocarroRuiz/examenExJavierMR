package com.mocarrojavier.examenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mocarrojavier.examenfinal.databinding.ActivityAddGameBinding

class AddGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGameBinding

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore



        binding.btnRegisterGame.setOnClickListener{
            val name: String = binding.tilName.editText?.text.toString()
            val steam: String = binding.tilSteam.editText?.text.toString()
            val precio: String = binding.tilCheapest.editText?.text.toString()
            val detalle: String = binding.tilDetalle.editText?.text.toString()

            if(name.isNotEmpty()&&steam.isNotEmpty()&&precio.isNotEmpty()&&detalle.isNotEmpty()){
                val games = hashMapOf(
                    "name" to name,
                    "steam" to steam,
                    "detalle" to detalle,
                    "cheapest" to precio
                )
                firestore.collection("lego")
                    .add(games)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Agregado correctamente: ${it.id}", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"No se agrego el elemento", Toast.LENGTH_SHORT).show()
                    }

            }


        }

    }
}
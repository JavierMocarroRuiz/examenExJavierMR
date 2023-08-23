package com.mocarrojavier.examenfinal.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mocarrojavier.examenfinal.AddGameActivity
import com.mocarrojavier.examenfinal.R
import com.mocarrojavier.examenfinal.RVJuegoListAdapter
import com.mocarrojavier.examenfinal.databinding.FragmentListadoBinding
import com.mocarrojavier.examenfinal.model.Game
import com.mocarrojavier.examenfinal.model.Games

class ListadoFragment : Fragment() {


    private lateinit var binding : FragmentListadoBinding
    private lateinit var viewModel : JuegoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[JuegoListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListadoBinding.inflate(inflater, container, false)
        binding.fabAddGame.setOnClickListener {
            val intent = Intent(requireContext(), AddGameActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVJuegoListAdapter(listOf())
        binding.rvGameList.adapter = adapter
        viewModel.getJuegoList().addOnSuccessListener { querySnapshot ->
            val serieList = mutableListOf<Game>()
            for (document in querySnapshot.documents) {
                val name = document.getString("name") ?: ""
                val detalle = document.getString("detalle") ?: ""
                val steam = document.getString("steam") ?: ""
                val cheapest = document.getString("cheapest") ?: ""
                serieList.add(
                    Game(steam,cheapest,name,detalle)
                )
            }
            adapter.setData(serieList)
        }
    }

}


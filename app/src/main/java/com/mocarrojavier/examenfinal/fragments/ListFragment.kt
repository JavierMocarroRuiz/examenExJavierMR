package com.mocarrojavier.examenfinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mocarrojavier.examenfinal.R
import com.mocarrojavier.examenfinal.RVGamesListAdapter
import com.mocarrojavier.examenfinal.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private  lateinit var binding: FragmentListBinding

    private lateinit var viewModel: ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVGamesListAdapter(listOf())
            binding.rvGameList.adapter = adapter
            viewModel.games.observe(this){games ->
            games?.let {
                adapter.games = it
                adapter.notifyDataSetChanged()
            }
        }
        //binding.rvGamesList.adapter = adapter
        //binding.rvGamesList.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
        //viewModel.gameList.observe(requireActivity())
        viewModel.getGamesList()
    }

}


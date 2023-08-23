package com.mocarrojavier.examenfinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.mocarrojavier.examenfinal.R
import com.mocarrojavier.examenfinal.databinding.FragmentDetailBinding
import com.mocarrojavier.examenfinal.model.Games

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private lateinit var games: Games
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        games = args.games
        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgGames.setImageResource(R.drawable.ic_batman_logo2)
        binding.txtInternalname.text = games.internalName
        binding.txtCheapest.text = games.cheapest
        binding.txtExternal.text = games.external
        binding.txtSteam.text = games.steam

        binding.btnAddfavorite.setOnClickListener{
            viewModel.addFavorite(games)
        }
    }

}
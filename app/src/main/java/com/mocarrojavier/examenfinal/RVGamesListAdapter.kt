package com.mocarrojavier.examenfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mocarrojavier.examenfinal.databinding.ItemgamesBinding
import com.mocarrojavier.examenfinal.model.Game
import com.mocarrojavier.examenfinal.model.Games

//, val onClick: (Games) -> Unit)

class RVGamesListAdapter(var games: List<Games>): RecyclerView.Adapter<GamesVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesVH {
        val binding = ItemgamesBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return GamesVH(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GamesVH, position: Int) {
        holder.bind(games[position])
    }

}

class GamesVH(private val binding: ItemgamesBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(games: Games) {
        binding.imageView.setImageResource(R.drawable.ic_batman_logo)
        binding.txtInternalname.text = games.internalName
        binding.txtCheapest.text = games.cheapest
        binding.txtExternal.text = games.external
        binding.txtSteam.text = games.steam

    }

}
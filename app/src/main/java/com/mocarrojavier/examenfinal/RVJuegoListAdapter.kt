package com.mocarrojavier.examenfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mocarrojavier.examenfinal.databinding.ItemgamesBinding
import com.mocarrojavier.examenfinal.model.Game

class RVJuegoListAdapter(var series : List<Game>) : RecyclerView.Adapter<JuegoViewHolder>() {


        fun setData(newSeries : List<Game>) {
            series = newSeries
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuegoViewHolder {
            val binding = ItemgamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return JuegoViewHolder(binding)
        }

        override fun getItemCount() : Int = series.size

        override fun onBindViewHolder(holder: JuegoViewHolder, position: Int) {
            holder.bind(series[position])
        }

    }

    class JuegoViewHolder(private val binding : ItemgamesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (tvserie : Game) {
            binding.txtExternal.text = "Nombre: ${tvserie.name}"
            binding.txtCheapest.text = "Costo: ${tvserie.cheapest}"
            binding.txtInternalname.text = "Detalle: ${tvserie.detalle}"
            binding.txtSteam.text = "Steam: ${tvserie.steam}"
        }
    }



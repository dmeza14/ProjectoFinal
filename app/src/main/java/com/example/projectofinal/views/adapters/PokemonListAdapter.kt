package com.example.projectofinal.views.adapters

import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinal.R
import com.example.projectofinal.network.models.PokemonList
import com.squareup.picasso.Picasso

//Implementamos el adapter para pintar el RecyclerView
class PokemonListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    //Definimos la lista de pokemones
    var pokemonList: List<PokemonList> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //Creamos el ViewHolder, encargado de pintar y escuchar clicks de cada celda
    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var pokemonNameTextView: TextView = itemView.findViewById(R.id.pokemon_cell_name)
        private var pokemonSubtitleTextView: TextView = itemView.findViewById(R.id.pokemon_subtitle)
        private var pokemonImageView: ImageView = itemView.findViewById(R.id.pokemon_image)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(model: PokemonList) {
            //Llenamos los campos de la celda
            pokemonNameTextView.text = model.name
            pokemonSubtitleTextView.text = model.subtitle
            Picasso.get()
                .load(model.imageUrl)
                .into(pokemonImageView)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    //Inflamos la vista de la celda y la pasamos al ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val holderView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_cell, parent, false)
        return PokemonViewHolder(holderView)
    }

    //Pintamos la información de la celda para cada posición
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    //Devolvemos el tamaño de la lista
    override fun getItemCount(): Int = pokemonList.size
}
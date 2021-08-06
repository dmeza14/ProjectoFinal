package com.example.projectofinal.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(@PrimaryKey val name: String)

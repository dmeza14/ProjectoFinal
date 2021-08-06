package com.example.projectofinal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDB: RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO

    companion object{
        @Volatile
        private var INSTANCE: PokemonDB? = null

        fun getDatabase(context: Context): PokemonDB{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                PokemonDB::class.java,
                "pokemon_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
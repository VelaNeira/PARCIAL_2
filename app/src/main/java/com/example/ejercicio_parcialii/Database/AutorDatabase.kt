package com.example.ejercicio_parcialii.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ejercicio_parcialii.DAO.AutorDao
import com.example.ejercicio_parcialii.Model.Autor

//Abstract es usado para evitar crear nuevas instancias de la BD y room gestiona la relacion
@Database(entities = [Autor::class], version = 1, exportSchema = false)
abstract class AutorDatabase: RoomDatabase() {
    abstract fun autorDao(): AutorDao

    companion object {
        @Volatile
        private  var INSTANCE: AutorDatabase? = null

        // Permitir crear una instancia en la BD
        fun getDatabase(context: Context): AutorDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AutorDatabase::class.java,
                    "autordatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
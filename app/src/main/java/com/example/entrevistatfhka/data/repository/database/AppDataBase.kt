package com.example.entrevistatfhka.data.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.data.repository.Dao.DaoRecibo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ModelRecibo::class,ModelProducto::class], version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract fun daoRecibo(): DaoRecibo

    companion object {

        private var INSTANCE: AppDataBase? = null

        //Para iniciar una corrutina, necesitas un CoroutineScope. se nececito Actualizar
        // el método getDatabase de la clase AppDataBase a fin de obtener también un
        // alcance de corrutina como parámetro:

        fun getDatabase(context: Context, scope: CoroutineScope): AppDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "practica_database"
                ).build()
                    //.addCallback(AppDataBaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance

            }


        }

    }
    }
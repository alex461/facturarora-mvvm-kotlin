package com.example.entrevistatfhka.data.repository.Dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.entrevistatfhka.data.domain.ModelFactura
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.domain.ModelRecibo
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoRecibo {

    @Query("SELECT * FROM recibo_table")
    fun getListRecibo(): Flow<MutableList<ModelRecibo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarUsuario(recibo: ModelRecibo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarListaProducto(productos: MutableList<ModelProducto>)


    @Query("DELETE FROM recibo_table")
    suspend fun deleteAll()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertProducts(products:MutableList<ModelProducto>)

    @Query("SELECT * FROM producto_table")
    suspend fun  getProducts():MutableList<ModelProducto>


    @Transaction
    @Query("SELECT * FROM recibo_table")
    fun getListaDeFacturas():Flow<List<ModelFactura>>
}
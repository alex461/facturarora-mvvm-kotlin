package com.example.entrevistatfhka.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.entrevistatfhka.data.SettingPreference
import com.example.entrevistatfhka.data.domain.ModelFactura
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.data.repository.Dao.DaoRecibo
import com.example.entrevistatfhka.utils.Constante
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class BaseRepository(private val daoRecibo: DaoRecibo,private val settingPreference: SettingPreference){


  //  val listaDeRecibo: Flow<List<ModelRecibo>> = daoRecibo.getListRecibo()

    val listaDefacturas: Flow<List<ModelFactura>> = daoRecibo.getListaDeFacturas()


    val listProductos = MutableLiveData<MutableList<ModelProducto>>().apply {
        value = mutableListOf()
    }



  // val  mMap = HashMap<String, List<String>>() getDataStatus(repo)
    //    .forEach{ (s, list) -> mMap?.put(s, list.toList()) }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun guardarFactura() {
        val preference = settingPreference.userPreferences.first()
        daoRecibo.guardarUsuario(ModelRecibo(0,preference.cedula,"${preference.userName} ${preference.apellido}",
        preference.razonSocialEmisor,"${preference.tipoRif} ${preference.numeroDeRif}"))

        settingPreference.borrarDatosTemporales()


    }



    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun guardarProductos() {

        listProductos.value?.let {
            daoRecibo.insertProducts(it.toMutableList()) }

        listProductos.value?.clear()
    }





    fun addProductToList(producto: ModelProducto) {

        listProductos.value!!.add(producto)
         listProductos.value = listProductos.value
    }




     suspend fun validateData(dato1:String, datos2 :String, dato3:String, campo:String):Boolean{

       if(dato1.isNotEmpty()&&datos2.isNotEmpty()&&dato3.isNotEmpty()){

           if (campo.equals(Constante.CAMPO_USUARIO)){
           settingPreference.guardarDatosUsuario(dato1,datos2,dato3.toInt())
           }

           if (campo.equals(Constante.CAMPO_RAZON_SOCIAL)){
               settingPreference.guardaDatosIdentificador(dato1,datos2,dato3.toInt())
           }

        return true
       }
   return false
    }





}
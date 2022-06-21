package com.example.entrevistatfhka

import android.app.Application
import com.example.entrevistatfhka.data.SettingPreference
import com.example.entrevistatfhka.data.repository.BaseRepository
import com.example.entrevistatfhka.data.repository.database.AppDataBase
import com.example.entrevistatfhka.view.adapter.ProductoAdpater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FacturaAplicacion : Application(){


    val applicationScope = CoroutineScope(SupervisorJob())

    val dataBase by lazy { AppDataBase.getDatabase(this,applicationScope) }

    val settingPreference by lazy { SettingPreference(this) }

    val repositorio by lazy { BaseRepository(dataBase.daoRecibo(),settingPreference) }

   // private val productoAdpater by lazy {  ProductoAdpater()

   // }

}
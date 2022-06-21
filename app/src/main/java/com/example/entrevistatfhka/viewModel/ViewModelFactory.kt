package com.example.entrevistatfhka.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.entrevistatfhka.data.repository.BaseRepository

class ViewModelFactory (private  val repositorio: BaseRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repositorio) as T
        }
        throw IllegalArgumentException("View Model Desconocido")}
}
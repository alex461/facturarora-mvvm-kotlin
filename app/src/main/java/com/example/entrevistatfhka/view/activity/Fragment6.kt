package com.example.entrevistatfhka.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entrevistatfhka.databinding.Fragment6Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.view.adapter.FacturaAdapter
import com.example.entrevistatfhka.view.adapter.ProductoAdpater
import com.example.entrevistatfhka.view.adapter.ReciboAdapter
import com.example.entrevistatfhka.viewModel.MyViewModel

class Fragment6 : BaseFragment<MyViewModel, Fragment6Binding>() {

    private val facturaAdpater by lazy {  FacturaAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    binding.rvRecibos.apply {
        layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
        adapter = facturaAdpater

    }

    viewModel.todasLasFacturas.observe(viewLifecycleOwner){ facturas ->

        binding.rvRecibos.also { recycler ->

        binding.tvListaVacia.isVisible = recycler.size <= 0

           recycler.adapter = ReciboAdapter(facturas)
        }



    }




    }

    override fun getViewModel() = MyViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = Fragment6Binding.inflate(inflater,container,false)

}
package com.example.entrevistatfhka.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.databinding.Fragment4Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.utils.Constante
import com.example.entrevistatfhka.view.adapter.ProductoAdpater
import com.example.entrevistatfhka.viewModel.MyViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first


class Fragment4 : BaseFragment<MyViewModel, Fragment4Binding>() {

    private var cantidad:Int=0
    private var cedula : Int =0



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.recyclerViewProducto.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        }

        //---------------------- botones de cantidad de productos

        binding.imageButtonAddCantidad.setOnClickListener {
            viewModel.aumentarCantidadProducto() }

        binding.imageButtonRestarCantidad.setOnClickListener {
            viewModel.disminuirCantidadProducto() }


        //metodo para crear un dato en comun entre los productos con el recibo
        lifecycleScope.launchWhenStarted {
          cedula =  settingPreferences.userPreferences.first().cedula

        }


        lifecycleScope.launchWhenStarted {

            viewModel.cantidaDeProductos.collect { cantidadDeProductos ->

                binding.etCantidad.setText(cantidadDeProductos.toString())

            }

        }


        with(binding) {


            imageButtonAddProducto.setOnClickListener {

                viewModel.onSubmitClicked(
                    etCodigo.text.toString(),
                    etDescripcion.text.toString(),
                    etPrecio.text.toString(),
                    Constante.CAMPO_PRODUCTO
                )

            }

        }


        viewModel.totales.observe(viewLifecycleOwner) { precioTotal ->

            binding.tvSubtotal.text = precioTotal.toString()

        }



        viewModel.loginResult.observe(viewLifecycleOwner) { success ->

            if (success) {

                with(binding) {

                    viewModel.agregarProducto(
                        ModelProducto(
                             productocuenta = cedula,
                            codigo = etCodigo.text.toString().toInt(),
                            descripcion = etDescripcion.text.toString(),
                           precio =  etPrecio.text.toString().toDouble(),
                            cantidad = etCantidad.text.toString().toInt()
                        )
                    )

                    viewModel.restarCantidadProducto()

                    etCodigo.text!!.clear()
                    etDescripcion.text!!.clear()
                    etPrecio.text!!.clear()

                    binding.tfCodigo.error = null
                    binding.tfDescripcion.error = null
                    binding.tfPrecio.error = null

                }
            }

        }


        //---------------- se verifica que no tenga campo vacio

        lifecycleScope.launchWhenResumed {

            with(binding) {


                etCodigo.doOnTextChanged { text, start, count, after ->

                    if (after == 0) tfCodigo.error = "Debe ingresar el código"
                    else tfCodigo.error = ""
                }

                etDescripcion.doOnTextChanged { text, start, count, after ->
                    if (after == 0) tfDescripcion.error = "Debe ingresar la descripción"
                    else tfDescripcion.error = ""
                }


                etPrecio.doOnTextChanged { text, start, count, after ->
                    if (after == 0) tfPrecio.error = "Debe ingresar el precio"
                    else tfPrecio.error = ""
                }


            }


        }



//---------------------------------------------------------------------

        viewModel.listProducts.observe(viewLifecycleOwner) { productos ->

            binding.recyclerViewProducto.also { recycler ->
                recycler.adapter = ProductoAdpater(productos)
            }
                cantidad = productos.size
        }



        binding.buttonTotalizar.setOnClickListener {

            if (cantidad != 0) {

                findNavController().navigate(R.id.action_fragment4_to_fragment5)


            } else {

                Toast.makeText(
                    requireContext(),
                    "debes de ingresar un producto",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }


            binding.buttonAnterior.setOnClickListener {

                findNavController().navigate(R.id.action_fragment4_to_fragment3)


            }


    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = Fragment4Binding.inflate(inflater, container, false)

    override fun getViewModel() = MyViewModel::class.java


}
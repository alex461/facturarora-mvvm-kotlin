package com.example.entrevistatfhka.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.databinding.Fragment3Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.utils.Constante
import com.example.entrevistatfhka.viewModel.MyViewModel
import kotlinx.coroutines.flow.first


class Fragment3 : BaseFragment<MyViewModel, Fragment3Binding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.identificador,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }


        binding.buttonSiguiente.setOnClickListener {

            viewModel.onSubmitClicked(
                binding.etRazonSocial.text.toString(),
                binding.spinner.selectedItem.toString(),
                binding.etRif.text.toString(),
                Constante.CAMPO_RAZON_SOCIAL
            )


        }


        lifecycleScope.launchWhenResumed {

            read()

        }

        viewModel.loginResult.observe(viewLifecycleOwner) {

                success ->

            if (success) {



                findNavController().navigate(R.id.action_fragment3_to_fragment4)

            }

        }


        with(binding) {


            etRazonSocial.doOnTextChanged { text, start, count, after ->

                if (after == 0)

                    tfRazonSocial.error = "Debe ingresar un nombre"
                else tfRazonSocial.error = ""
            }

            etRif.doOnTextChanged { text, start, count, after ->
                if (after == 0) tfRif.error = "Debe ingresar un nombre"
                else tfRif.error = ""
            }


        }


    }

    private suspend fun read (){



        var dd =  settingPreferences.userPreferences.first()

        Toast.makeText(requireContext(),dd.userName+"",Toast.LENGTH_SHORT).show()

        var dd2 = ModelRecibo(1,0,"","","")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = Fragment3Binding.inflate(inflater, container, false)


    override fun getViewModel() = MyViewModel::class.java

}
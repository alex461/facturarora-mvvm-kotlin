package com.example.entrevistatfhka.view.activity

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.databinding.Fragment2Binding
import com.example.entrevistatfhka.utils.BaseFragment
import com.example.entrevistatfhka.utils.Constante
import com.example.entrevistatfhka.viewModel.MyViewModel


class Fragment2 : BaseFragment<MyViewModel, Fragment2Binding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnSiguiente.setOnClickListener {

            viewModel.onSubmitClicked(
                binding.etNombre.text.toString(),
                binding.etApellido.text.toString(),
                binding.etCedula.text.toString(),
                Constante.CAMPO_USUARIO
            )





        }

        binding.btnSalir.setOnClickListener {

            findNavController().navigate(R.id.action_fragment2_to_fragment1)

        }


            lifecycleScope.launchWhenResumed {

                with(binding) {


                    etNombre.doOnTextChanged { text, start, count, after ->

                        if (after == 0)

                            tfNombre.error = "Debe ingresar un nombre"
                        else tfNombre.error = ""
                    }

                    etApellido.doOnTextChanged { text, start, count, after ->
                        if (after == 0) tfApellido.error = "Debe ingresar un nombre"
                        else tfApellido.error = ""
                    }

                    etCedula.doOnTextChanged { text, start, count, after ->
                        if (after == 0) tfCedula.error = "Debe ingresar un nombre"
                        else tfCedula.error = ""
                    }
                }

            }






        viewModel.loginResult.observe(viewLifecycleOwner) {

                success ->
            if (success) {

                findNavController().navigate(R.id.action_fragment2_to_fragment3)
            }
        }



        settingPreferences.userPreferences.asLiveData().observe(viewLifecycleOwner){ preference ->

            if (preference.userName!="" && preference.apellido!=""){
            binding.etNombre.setText(preference.userName)
            binding.etApellido.setText(preference.apellido)
            }

            if (preference.cedula!=0){ binding.etCedula.setText(preference.cedula.toString()) }

        }



    }




    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = Fragment2Binding.inflate(inflater, container, false)

    override fun getViewModel() = MyViewModel::class.java


}
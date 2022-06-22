package com.example.entrevistatfhka.viewModel



import androidx.lifecycle.*
import com.example.entrevistatfhka.data.domain.ModelFactura
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(private val repositorio: BaseRepository):ViewModel() {


    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult :LiveData<Boolean> get() = _loginResult

    private val _menssageToast = Channel<String> ()
    val menssageToast = _menssageToast.receiveAsFlow()


    private val _messageError = MutableLiveData<String>()
    val messageError :LiveData<String> get() = _messageError


    private val _cantidadDeProductos = MutableStateFlow(1)
    val cantidaDeProductos : StateFlow<Int> = _cantidadDeProductos



    val listProducts : MutableLiveData<MutableList<ModelProducto>> = repositorio.listProductos


   // val todoLosRecibo: LiveData<List<ModelRecibo>> = repositorio.listaDeRecibo.asLiveData()

    val todasLasFacturas: LiveData<List<ModelFactura>> = repositorio.listaDefacturas.asLiveData()


    fun guardarRecibo () = viewModelScope.launch {
        repositorio.guardarFactura()
        repositorio.guardarProductos()

    }


   // val listProducts: MutableList<ModelProducto> = repositorio.listProductos



    fun agregarProducto(producto: ModelProducto) = viewModelScope.launch {
        repositorio.addProductToList(producto)
    }


    //metodo observable el cual obtiene todos los productos lo
    // transforma multiplica sus valores y recolecta los totales
    val totales = Transformations.map(listProducts){ precio ->

        precio.sumOf { it.cantidad*it.precio }

    }




    fun onSubmitClicked(data1:String,data2 :String,data3:String,campo:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {repositorio.validateData(data1,data2,data3,campo)}
            _loginResult.value = result
        }
    }



    fun aumentarCantidadProducto(){

        if (_cantidadDeProductos.value<100)
        _cantidadDeProductos.value += 1
    }

    fun disminuirCantidadProducto(){

        if (_cantidadDeProductos.value>1)
        _cantidadDeProductos.value -= 1

    }

    fun restarCantidadProducto(){
        _cantidadDeProductos.value = 1
    }


}






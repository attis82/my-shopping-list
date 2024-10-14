package com.farkasatesz.myshoppinglist.models.supermarket

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SupermarketViewModel(private val supermarketRepository: SupermarketRepository) : BaseViewModel<Supermarket>(supermarketRepository) {
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    private val _location = MutableStateFlow("")
    val location = _location.asStateFlow()

    fun setLocation(location: String) {
        _location.value = location
    }

    fun checkIfSupermarketExists(name: String, location: String){
        viewModelScope.launch {
            try {
                _exists.value = supermarketRepository.checkExistence(name, location)
            } catch (e: Exception) {
                Log.e("SupermarketViewModel", "Error checking existence", e)
            }
        }
    }

}
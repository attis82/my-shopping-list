package com.farkasatesz.myshoppinglist.models.supermarket

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SupermarketViewModel(private val supermarketRepository: SupermarketRepository) : BaseViewModel<Supermarket>(supermarketRepository) {
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    private val _location = MutableStateFlow("")
    val location = _location.asStateFlow()

    private val _supermarketFromRef = MutableStateFlow<Supermarket?>(null)
    val supermarketFromRef = _supermarketFromRef.asStateFlow()

    private val _supermarketRef = MutableStateFlow<DocumentReference?>(null)
    val supermarketRef = _supermarketRef.asStateFlow()

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

    fun getSupermarketReference(supermarketId: String){
        viewModelScope.launch {
            try {
                _supermarketRef.value = supermarketRepository.getSupermarketReference(supermarketId)
            }catch (e: Exception){
                Log.e("SupermarketViewModel", "Error getting supermarket reference", e)
            }
        }
    }

    fun getSupermarket(supermarketRef: DocumentReference){
        viewModelScope.launch {
            try {
                _supermarketFromRef.value = supermarketRepository.getSupermarketFromReference(supermarketRef)
            }catch (e: Exception){
                Log.e("SupermarketViewModel", "Error getting supermarket", e)
            }
        }
    }


}
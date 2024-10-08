package com.farkasatesz.myshoppinglist.models.product

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : BaseViewModel<Product>(productRepository) {
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    fun checkIfProductExists(name: String, unitType: DocumentReference){
        viewModelScope.launch {
            try {
                _exists.value = productRepository.checkExistence(name, unitType)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error checking existence", e)
            }
        }
    }
}
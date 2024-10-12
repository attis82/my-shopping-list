package com.farkasatesz.myshoppinglist.models.product

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : BaseViewModel<Product>(productRepository) {
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    private val _quantity = MutableStateFlow("")
    val quantity = _quantity.asStateFlow()

    private val _price = MutableStateFlow("")
    val price = _price.asStateFlow()

    fun setQuantity(quantity: String){
        _quantity.value = quantity
    }

    fun setPrice(price: String){
        _price.value = price
    }

    fun checkIfProductExists(name: String, unitTypeId: String){
        viewModelScope.launch {
            try {
                _exists.value = productRepository.checkExistence(name, unitTypeId)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error checking existence", e)
            }
        }
    }

}
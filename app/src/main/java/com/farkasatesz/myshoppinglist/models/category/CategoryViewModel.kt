package com.farkasatesz.myshoppinglist.models.category

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository : CategoryRepository) : BaseViewModel<Category>(categoryRepository){
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()


    fun checkIfCategoryExists(name: String) {
        viewModelScope.launch {
            try {
                _exists.value = categoryRepository.checkExistence(name)
            }catch (e: Exception) {
                Log.e("CategoryViewModel", "Error checking existence", e)
            }
        }
    }
}
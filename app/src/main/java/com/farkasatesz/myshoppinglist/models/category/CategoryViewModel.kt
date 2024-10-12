package com.farkasatesz.myshoppinglist.models.category

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository : CategoryRepository) : BaseViewModel<Category>(categoryRepository){
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    private val _category = MutableStateFlow<Category?>(null)
    val category = _category.asStateFlow()

    private val _categoryRef = MutableStateFlow<DocumentReference?>(null)
    val categoryRef = _categoryRef.asStateFlow()

    fun checkIfCategoryExists(name: String) {
        viewModelScope.launch {
            try {
                _exists.value = categoryRepository.checkExistence(name)
            }catch (e: Exception) {
                Log.e("CategoryViewModel", "Error checking existence", e)
            }
        }
    }

    fun getCategory(ref: DocumentReference){
        viewModelScope.launch {
            try {
                _category.value = categoryRepository.getCategoryFromReference(ref)
            }catch (e: Exception) {
                Log.e("CategoryViewModel", "Error getting category", e)
            }
        }
    }

    fun getCategoryReference(categoryId: String){
        viewModelScope.launch {
            try {
                _categoryRef.value = categoryRepository.getCategoryReference(categoryId)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error getting category reference", e)
            }
        }
    }

}
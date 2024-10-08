package com.farkasatesz.myshoppinglist.models.unitType

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UnitTypeViewModel(private val unitTypeRepository: UnitTypeRepository) : BaseViewModel<UnitType>(unitTypeRepository) {
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    fun checkIfUnitTypeExists(name: String){
        viewModelScope.launch {
            try {
                _exists.value = unitTypeRepository.checkExistence(name)
            } catch (e: Exception) {
                Log.e("UnitTypeViewModel", "Error checking existence", e)
            }
        }
    }
}
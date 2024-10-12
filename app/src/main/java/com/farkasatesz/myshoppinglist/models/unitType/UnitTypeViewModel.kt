package com.farkasatesz.myshoppinglist.models.unitType

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UnitTypeViewModel(private val unitTypeRepository: UnitTypeRepository) : BaseViewModel<UnitType>(unitTypeRepository) {
    private val _exists = MutableStateFlow(false)
    val exists = _exists.asStateFlow()

    private val _unitTypeFromRef = MutableStateFlow<UnitType?>(null)
    val unitTypeFromRef = _unitTypeFromRef.asStateFlow()

    private val _unitTypeToRef = MutableStateFlow<DocumentReference?>(null)
    val unitTypeToRef = _unitTypeToRef.asStateFlow()

    fun checkIfUnitTypeExists(name: String){
        viewModelScope.launch {
            try {
                _exists.value = unitTypeRepository.checkExistence(name)
            } catch (e: Exception) {
                Log.e("UnitTypeViewModel", "Error checking existence", e)
            }
        }
    }

    fun getUnitTypeFromReference(unitTypeReference: DocumentReference){
        viewModelScope.launch {
            try {
                _unitTypeFromRef.value = unitTypeRepository.getUnitTypeFromReference(unitTypeReference)
            }catch (e: Exception){
                Log.e("UnitTypeViewModel", "Error getting unit type reference", e)
            }
        }
    }

    fun getUnitTypeRef(unitTypeId: String){
        viewModelScope.launch {
            try{
                _unitTypeToRef.value = unitTypeRepository.getUnitTypeReference(unitTypeId)
            }catch (e: Exception){
                Log.e("UnitTypeViewModel", "Error getting unit type reference", e)
            }
        }
    }
}
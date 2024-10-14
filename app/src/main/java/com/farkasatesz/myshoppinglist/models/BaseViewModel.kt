package com.farkasatesz.myshoppinglist.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
open class BaseViewModel<T : BaseEntity>(private val baseRepository: BaseRepository<T>) : ViewModel(){

    private val _selected = MutableStateFlow<T?>(null)
    val selected = _selected.asStateFlow()

    private val _itemName = MutableStateFlow( "")
    val itemName = _itemName.asStateFlow()

    private val _editName = MutableStateFlow("")
    val editName = _editName.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _entityNames = MutableStateFlow<List<String>>(emptyList())
    val entityNames = _entityNames.asStateFlow()

    private val _items = _itemName.debounce(500).flatMapLatest {
        if (it.isEmpty()) {
            baseRepository.getAll()
        } else {
            baseRepository.getByQuery(it)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val items = _items

    fun setSelected(item: T) {
        _selected.value = item
    }

    init {
        getEntityNames()
    }

    private fun getEntityNames(){
        viewModelScope.launch {
            try {
               baseRepository.getEntityNames()
                   .distinctUntilChanged()
                   .collect{
                       _entityNames.value = it
                   }
            }catch (e: Exception){
                Log.e("BaseViewModel", "Error getting entity names", e)
            }
        }
    }

    fun setEditName(name: String) {
        _editName.value = name
    }

    fun setItemName(name: String) {
        _itemName.value = name
    }

    fun showDialog() {
        _showDialog.value = true
    }

    fun hideDialog() {
        _showDialog.value = false
    }

    fun create(item: T) {
        viewModelScope.launch {
            try {
                baseRepository.create(item)
                refresh()
            }catch (e: Exception) {
                Log.e("BaseViewModel", "Error creating item", e)
            }
        }
    }

    fun update(item: T) {
        viewModelScope.launch {
            try {
                baseRepository.update(item)
                refresh()
            } catch (e: Exception) {
                Log.e("BaseViewModel", "Error updating item", e)
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            try {
                baseRepository.delete(id)
                _selected.value = null
                refresh()
            } catch (e: Exception) {
                Log.e("BaseViewModel", "Error deleting item", e)
            }
        }
    }

    private fun refresh(){
        _itemName.value = " "
        _itemName.value = ""
    }

}
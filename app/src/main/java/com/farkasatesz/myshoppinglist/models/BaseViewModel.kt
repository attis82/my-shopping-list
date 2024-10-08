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
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _items = _query.debounce(500).flatMapLatest {
        if (it.isEmpty()) {
            baseRepository.getAll()
        } else {
            baseRepository.getByQuery(it)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val items = _items

    fun setQuery(query: String) {
        _query.value = query
    }

    fun create(item: T) {
        viewModelScope.launch {
            try {
                baseRepository.create(item)
            }catch (e: Exception) {
                Log.e("BaseViewModel", "Error creating item", e)
            }
        }
    }

    fun update(item: T) {
        viewModelScope.launch {
            try {
                baseRepository.update(item)
            } catch (e: Exception) {
                Log.e("BaseViewModel", "Error updating item", e)
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            try {
                baseRepository.delete(id)
            } catch (e: Exception) {
                Log.e("BaseViewModel", "Error deleting item", e)
            }
        }
    }

}
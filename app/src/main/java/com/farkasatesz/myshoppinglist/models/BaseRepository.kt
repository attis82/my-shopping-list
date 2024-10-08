package com.farkasatesz.myshoppinglist.models

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl

open class BaseRepository<T : BaseEntity>(private val fireStoreImpl: FireStoreImpl<T>) {
    suspend fun create(item: T) = fireStoreImpl.create(item)
    suspend fun update(item: T) = fireStoreImpl.update(item)
    suspend fun delete(id: String) = fireStoreImpl.delete(id)
    fun getAll() = fireStoreImpl.getAll()
    fun getById(id: String) = fireStoreImpl.getById(id)
    fun getByQuery(query: String) = fireStoreImpl.getByQuery(query)
}
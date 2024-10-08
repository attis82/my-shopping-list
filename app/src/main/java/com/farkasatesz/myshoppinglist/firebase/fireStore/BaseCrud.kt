package com.farkasatesz.myshoppinglist.firebase.fireStore

import kotlinx.coroutines.flow.Flow

interface BaseCrud <T> {
    suspend fun create(item : T)
    suspend fun update(item : T)
    suspend fun delete(id: String)
    fun getAll(): Flow<List<T>>
    fun getById(id: String): Flow<T>
    fun getByQuery(query: String): Flow<List<T>>
}

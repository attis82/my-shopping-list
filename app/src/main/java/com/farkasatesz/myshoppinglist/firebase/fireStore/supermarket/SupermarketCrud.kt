package com.farkasatesz.myshoppinglist.firebase.fireStore.supermarket

import kotlinx.coroutines.flow.Flow

interface SupermarketCrud {
    suspend fun checkExistence(name: String, location: String): Boolean
    fun getSupermarketNames(): Flow<List<String>>
}
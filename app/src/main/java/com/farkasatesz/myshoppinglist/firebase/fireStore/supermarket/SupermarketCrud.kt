package com.farkasatesz.myshoppinglist.firebase.fireStore.supermarket

interface SupermarketCrud {
    suspend fun checkExistence(name: String, location: String): Boolean
}
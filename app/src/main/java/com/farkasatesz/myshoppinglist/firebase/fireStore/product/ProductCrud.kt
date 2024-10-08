package com.farkasatesz.myshoppinglist.firebase.fireStore.product

interface ProductCrud {
    suspend fun checkExistence(name: String, unitTypeId: String): Boolean
}
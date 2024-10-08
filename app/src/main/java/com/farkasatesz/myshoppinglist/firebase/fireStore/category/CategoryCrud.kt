package com.farkasatesz.myshoppinglist.firebase.fireStore.category

interface CategoryCrud {
    suspend fun checkExistence(name: String): Boolean
}
package com.farkasatesz.myshoppinglist.firebase.fireStore.product

import com.google.firebase.firestore.DocumentReference

interface ProductCrud {
    suspend fun checkExistence(name: String, unitType: DocumentReference): Boolean
}
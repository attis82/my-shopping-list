package com.farkasatesz.myshoppinglist.models.supermarket

import com.farkasatesz.myshoppinglist.firebase.fireStore.supermarket.SupermarketImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository
import com.google.firebase.firestore.DocumentReference

class SupermarketRepository(private val supermarketImpl: SupermarketImpl) : BaseRepository<Supermarket>(supermarketImpl) {
    suspend fun checkExistence(name: String, location: String) = supermarketImpl.checkExistence(name, location)
    suspend fun getSupermarketReference(supermarketId: String) = supermarketImpl.getSupermarketReference(supermarketId)
    suspend fun getSupermarketFromReference(supermarketRef: DocumentReference) = supermarketImpl.getSupermarketFromReference(supermarketRef)
}
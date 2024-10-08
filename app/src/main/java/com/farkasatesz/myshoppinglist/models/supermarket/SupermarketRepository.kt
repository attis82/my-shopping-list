package com.farkasatesz.myshoppinglist.models.supermarket

import com.farkasatesz.myshoppinglist.firebase.fireStore.supermarket.SupermarketImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository

class SupermarketRepository(private val supermarketImpl: SupermarketImpl) : BaseRepository<Supermarket>(supermarketImpl) {
    suspend fun checkExistence(name: String, location: String) = supermarketImpl.checkExistence(name, location)
}
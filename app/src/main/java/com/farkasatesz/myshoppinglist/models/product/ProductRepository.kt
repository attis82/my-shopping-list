package com.farkasatesz.myshoppinglist.models.product

import com.farkasatesz.myshoppinglist.firebase.fireStore.product.ProductImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository
import com.google.firebase.firestore.DocumentReference

class ProductRepository(private val productImpl: ProductImpl) : BaseRepository<Product>(productImpl) {
    suspend fun checkExistence(name: String, unitType: DocumentReference) = productImpl.checkExistence(name, unitType)
}
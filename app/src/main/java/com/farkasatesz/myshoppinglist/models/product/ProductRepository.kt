package com.farkasatesz.myshoppinglist.models.product

import com.farkasatesz.myshoppinglist.firebase.fireStore.product.ProductImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository

class ProductRepository(private val productImpl: ProductImpl) : BaseRepository<Product>(productImpl) {
    suspend fun checkExistence(name: String, unitTypeId: String) = productImpl.checkExistence(name, unitTypeId)
}
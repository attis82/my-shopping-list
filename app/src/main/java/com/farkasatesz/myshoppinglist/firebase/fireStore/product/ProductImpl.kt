package com.farkasatesz.myshoppinglist.firebase.fireStore.product

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl
import com.farkasatesz.myshoppinglist.models.category.Category
import com.farkasatesz.myshoppinglist.models.product.Product
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProductImpl(private val db: FirebaseFirestore)
    : FireStoreImpl<Product>(db, "products", Product::class.java), ProductCrud {
    override suspend fun checkExistence(name: String, unitTypeId: String): Boolean {
        val collection = db.collection("products")
        val unitTypeRef = db.document(unitTypeId)
        val snapshot = collection
            .whereEqualTo("entityName", name)
            .whereEqualTo("unitType", unitTypeRef)
            .get()
            .await()
        return !snapshot.isEmpty
    }
}
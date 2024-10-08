package com.farkasatesz.myshoppinglist.firebase.fireStore.product

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl
import com.farkasatesz.myshoppinglist.models.product.Product
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProductImpl(private val db: FirebaseFirestore)
    : FireStoreImpl<Product>(db, "products", Product::class.java), ProductCrud {
    override suspend fun checkExistence(name: String, unitType: DocumentReference): Boolean {
        val collection = db.collection("products")
        val snapshot = collection
            .whereEqualTo("entityName", name)
            .whereEqualTo("unitType", unitType)
            .get()
            .await()
        return !snapshot.isEmpty
    }

}
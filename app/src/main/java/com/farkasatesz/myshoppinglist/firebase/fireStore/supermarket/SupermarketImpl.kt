package com.farkasatesz.myshoppinglist.firebase.fireStore.supermarket

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl
import com.farkasatesz.myshoppinglist.models.supermarket.Supermarket
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SupermarketImpl(private val db: FirebaseFirestore) : FireStoreImpl<Supermarket>(db, "supermarkets", Supermarket::class.java), SupermarketCrud {
    override suspend fun checkExistence(name: String, location: String): Boolean {
        val collection = db.collection("supermarkets")
        val snapshot = collection
            .whereEqualTo("entityName", name)
            .whereEqualTo("location", location)
            .get()
            .await()
        return !snapshot.isEmpty
    }
}
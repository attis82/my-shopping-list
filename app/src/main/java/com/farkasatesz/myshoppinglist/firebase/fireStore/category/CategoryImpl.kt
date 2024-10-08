package com.farkasatesz.myshoppinglist.firebase.fireStore.category

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl
import com.farkasatesz.myshoppinglist.models.category.Category
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CategoryImpl(private val db: FirebaseFirestore) : FireStoreImpl<Category>(db, "categories", Category::class.java), CategoryCrud {
    override suspend fun checkExistence(name: String): Boolean {
        val collection = db.collection("categories")
        val snapshot = collection.whereEqualTo("entityName", name).get().await()
        return !snapshot.isEmpty
    }
}
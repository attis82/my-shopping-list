package com.farkasatesz.myshoppinglist.firebase.fireStore.unitType

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl
import com.farkasatesz.myshoppinglist.models.unitType.UnitType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UnitTypeImpl(private val db: FirebaseFirestore) : FireStoreImpl<UnitType>(db, "unitTypes", UnitType::class.java), UnitTypeCrud {
    override suspend fun checkExistence(name: String): Boolean {
        val collection = db.collection("unitTypes")
        val snapshot = collection.whereEqualTo("entityName", name).get().await()
        return !snapshot.isEmpty
    }
}
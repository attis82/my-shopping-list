package com.farkasatesz.myshoppinglist.firebase.fireStore

import android.util.Log
import com.farkasatesz.myshoppinglist.models.BaseEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

open class FireStoreImpl<T : BaseEntity>(
    db: FirebaseFirestore,
    collectionName: String,
    private val itemClass: Class<T>
) : BaseCrud<T> {

    private val collection = db.collection(collectionName)
    override suspend fun create(item: T) {
        try {
            val document = collection.add(item.toEntityMap()).await()
            item.entityId = document.id
            collection.document(document.id).set(item.toEntityMap()).await()
        } catch (e: Exception) {
            Log.e("FireStoreImpl", "Error creating document", e)
        }
    }

    override suspend fun update(item: T) {
        try {
            val snapshot = collection.document(item.entityId!!).get().await()
            if (snapshot.exists()) {
                collection.document(item.entityId!!).set(item.toEntityMap()).await()
            } else {
                Log.e("FireStoreImpl", "Document not found")
            }
        } catch (e: Exception) {
            Log.e("FireStoreImpl", "Error updating document", e)
        }
    }

    override suspend fun delete(id: String) {
        try {
            collection.document(id).delete().await()
        } catch (e: Exception) {
            Log.e("FireStoreImpl", "Error deleting document", e)
        }
    }

    override fun getAll(): Flow<List<T>> = flow {
        val snapshot = collection.get().await()
        val items = snapshot.documents.mapNotNull { doc ->
            doc.toObject(itemClass)?.apply {
                entityId = doc.id
            }
        }
        emit(items)
    }

    override fun getById(id: String): Flow<T> = flow {
        val snapshot = collection.document(id).get().await()
        val item = snapshot.toObject(itemClass)
        emit(item!!)
    }

    override fun getByQuery(query: String): Flow<List<T>> = flow {
        val snapshot = collection
            .whereGreaterThanOrEqualTo("entityName", query)
            .whereLessThanOrEqualTo("entityName", query + "\uf8ff")
            .get()
            .await()
        val items = snapshot.documents.mapNotNull { doc ->
            doc.toObject(itemClass)
        }
        emit(items)
    }

    override fun getEntityNames(): Flow<List<String>> = flow {
        val snapshot = collection.get().await()
        val names = snapshot.documents.mapNotNull { it.getString("entityName") }
        emit(names)
    }

}
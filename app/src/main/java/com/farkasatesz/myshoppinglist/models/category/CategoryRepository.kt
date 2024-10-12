package com.farkasatesz.myshoppinglist.models.category

import com.farkasatesz.myshoppinglist.firebase.fireStore.category.CategoryImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository
import com.google.firebase.firestore.DocumentReference

class CategoryRepository(private val categoryImpl: CategoryImpl): BaseRepository<Category>(categoryImpl){
    suspend fun checkExistence(name: String) = categoryImpl.checkExistence(name)
    suspend fun getCategoryFromReference(categoryRef: DocumentReference) = categoryImpl.getCategoryFromReference(categoryRef)
    suspend fun getCategoryReference(categoryId: String) = categoryImpl.getCategoryReference(categoryId)
}
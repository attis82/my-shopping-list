package com.farkasatesz.myshoppinglist.models.category

import com.farkasatesz.myshoppinglist.firebase.fireStore.category.CategoryImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository

class CategoryRepository(private val categoryImpl: CategoryImpl): BaseRepository<Category>(categoryImpl){
    suspend fun checkExistence(name: String) = categoryImpl.checkExistence(name)
}
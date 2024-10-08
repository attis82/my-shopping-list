package com.farkasatesz.myshoppinglist.models.category

import com.farkasatesz.myshoppinglist.firebase.fireStore.category.CategoryImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository

class CategoryRepository(categoryImpl: CategoryImpl): BaseRepository<Category>(categoryImpl)